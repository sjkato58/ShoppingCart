package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CheckOutPricesModel
import com.mtfuji.sakura.domain.repositories.CartRepository
import com.mtfuji.sakura.domain.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class DiscountManagerImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val discounts: List<Discounts>,
    private val cartRepository: CartRepository
): DiscountManager {

    override suspend fun calculateTotalDiscount(): CheckOutPricesModel =
        withContext(dispatcherProvider.io) {
            val sortedDiscounts = discounts.sortedByDescending { it.rating }

            val discountedProducts = mutableSetOf<String>()
            val appliedDiscounts = mutableListOf<AppliedDiscountModel>()

            val cartItemList = cartRepository.getItems()
            val totalCost = cartRepository.getTotalCost()

            var totalDiscount = 0.0
            for (discount in sortedDiscounts) {
                when (discount) {
                    is PercentageOffCartDiscount -> {
                        if (discount.canApplyDiscount(appliedDiscounts)) {
                            val cartDiscountAmount =
                                discount.applyToTotal(totalCost - totalDiscount)
                            totalDiscount += cartDiscountAmount
                            appliedDiscounts.add(
                                AppliedDiscountModel(
                                    productId = "Cart",
                                    discountName = discount.name,
                                    discountAmount = cartDiscountAmount
                                )
                            )
                        }
                    }
                    is BundleDiscount -> {
                        val eligibleCartItems = cartItemList.filterNot { discountedProducts.contains(it.product.id) }
                        val bundleDiscountAmount = discount.applyToCartList(eligibleCartItems)
                        if (bundleDiscountAmount > 0) {
                            totalDiscount += bundleDiscountAmount
                            discount.productIds.forEach { discountedProducts.add(it) }
                            appliedDiscounts.add(
                                AppliedDiscountModel(
                                    productId = "Bundle${discount.productIds}",
                                    discountName = discount.name,
                                    discountAmount = bundleDiscountAmount
                                )
                            )
                        }
                    }
                    else -> {
                        for (cartItem in cartItemList) {
                            if (discountedProducts.contains(cartItem.product.id)) continue

                            val discountAmount = discount.applyToProduct(cartItem.product, cartItem.quantity)
                            if (discountAmount > 0) {
                                totalDiscount += discountAmount
                                discountedProducts.add(cartItem.product.id)
                                appliedDiscounts.add(
                                    AppliedDiscountModel(
                                        productId = cartItem.product.id,
                                        discountName = discount.name,
                                        discountAmount = discountAmount
                                    )
                                )
                            }
                        }
                    }
                }
            }

            CheckOutPricesModel(
                finalTotal = totalCost - totalDiscount,
                totalDiscount = totalDiscount,
                appliedDiscounts = appliedDiscounts
            )
        }
}