package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CartItemModel

class SpecificProductDiscount(
    private val productId: String,
    private val discountPercentage: Double,
    override val rating: Int,
    override val name: String
): Discounts {

    override fun applyDiscount(
        appliedDiscounts: List<AppliedDiscountModel>,
        cartItemList: List<CartItemModel>
    ): AppliedDiscountModel? {
        val discountedProductIds = appliedDiscounts.obtainProductIds()
        for (cartItem in cartItemList) {
            if (discountedProductIds.contains(cartItem.product.id).not()
                && cartItem.product.id == productId) {
                val baseTotalProductPrice = cartItem.product.price * cartItem.quantity
                return AppliedDiscountModel(
                    productIds = listOf(cartItem.product.id),
                    discountName = name,
                    discountAmount = baseTotalProductPrice * (discountPercentage / 100),
                    discountPercentage = -1.0
                )
            }
        }
        return null
    }
}