package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.domainmodels.discounts.AppliedDiscountModel

class PercentageOffCartDiscount(
    private val percentage: Double,
    override val rating: Int,
    override val name: String
): Discounts {
    //fun applyToTotal(cartTotal: Double): Double = cartTotal * (percentage / 100)

    override fun applyDiscount(
        appliedDiscounts: List<AppliedDiscountModel>,
        cartItemList: List<CartItemModel>
    ): AppliedDiscountModel? {
        return if (appliedDiscounts.isEmpty()) {
            AppliedDiscountModel(
                productIds = listOf("Cart"),
                discountName = name,
                discountAmount = -1.0,
                discountPercentage = percentage
            )
        } else null
    }
}