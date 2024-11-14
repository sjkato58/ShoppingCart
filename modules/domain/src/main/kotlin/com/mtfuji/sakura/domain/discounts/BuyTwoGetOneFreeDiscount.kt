package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.AppliedDiscountModel
import com.mtfuji.sakura.domain.models.CartItemModel

class BuyTwoGetOneFreeDiscount(
    private val productId: String,
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
                val freeItems = cartItem.quantity / 3
                return AppliedDiscountModel(
                    productIds = listOf(cartItem.product.id),
                    discountName = name,
                    discountAmount = freeItems * cartItem.product.price,
                    discountPercentage = -1.0
                )
            }
        }
        return null
    }
}