package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.ProductModel

class BuyTwoGetOneFreeDiscount(
    private val productId: String,
    override val rating: Int,
    override val name: String
): Discounts {
    override fun applyToProduct(product: ProductModel, quantity: Int): Double {
        if (product.id != productId) return 0.0
        val freeItems = quantity / 3
        return freeItems * product.price
    }
}