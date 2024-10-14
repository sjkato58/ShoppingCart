package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.ProductModel

class SpecificProductDiscount(
    private val productId: String,
    private val discountPercentage: Double,
    override val rating: Int,
    override val name: String
): Discounts {
    override fun applyToProduct(product: ProductModel, quantity: Int): Double {
        if (product.id == productId) {
            return (product.price * quantity) * (discountPercentage / 100)
        }
        return 0.0
    }
}