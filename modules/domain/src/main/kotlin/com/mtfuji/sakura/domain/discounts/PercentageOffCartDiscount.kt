package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CartItemModel

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