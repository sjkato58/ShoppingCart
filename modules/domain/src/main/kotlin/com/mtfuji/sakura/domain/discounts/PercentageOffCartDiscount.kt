package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.ProductModel

class PercentageOffCartDiscount(
    private val percentage: Double,
    override val rating: Int,
    override val name: String
): Discounts {

    override fun applyToProduct(product: ProductModel, quantity: Int): Double = 0.0

    fun canApplyDiscount(
        appliedDiscounts: List<AppliedDiscountModel>
    ): Boolean {
        if (appliedDiscounts.isNotEmpty() && rating == 0) return false
        return true
    }

    fun applyToTotal(cartTotal: Double): Double = cartTotal * (percentage / 100)
}