package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.data.repositories.CartRepository
import com.mtfuji.sakura.domainmodels.cart.CheckOutPricesModel
import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel
import om.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class DiscountManagerImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val discounts: List<Discounts>,
    private val cartRepository: CartRepository
): DiscountManager {

    override suspend fun calculateTotalDiscount(): CheckOutPricesModel =
        withContext(dispatcherProvider.io) {
            val sortedDiscounts = discounts.sortedByDescending { it.rating }

            val appliedDiscounts = mutableListOf<AppliedDiscountModel>()

            val cartItemList = cartRepository.getItems()
            for (discount in sortedDiscounts) {
                discount.applyDiscount(
                    appliedDiscounts,
                    cartItemList
                )?.let {
                    appliedDiscounts.add(it)
                }
            }

            var totalDiscount = 0.0
            val totalCost = cartRepository.getTotalCost()
            appliedDiscounts.forEach { appliedDiscountModel ->
                if (appliedDiscountModel.discountPercentage != -1.0) {
                    totalDiscount = totalCost * (appliedDiscountModel.discountPercentage / 100)
                    return@forEach
                } else {
                    totalDiscount += appliedDiscountModel.discountAmount
                }
            }

            CheckOutPricesModel(
                finalTotal = totalCost - totalDiscount,
                totalDiscount = totalDiscount,
                appliedDiscounts = appliedDiscounts
            )
        }
}