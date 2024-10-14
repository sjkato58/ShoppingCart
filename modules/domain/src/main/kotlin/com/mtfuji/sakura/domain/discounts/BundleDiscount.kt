package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.models.ProductModel

class BundleDiscount(
    val productIds: List<String>,
    private val bundlePrice: Double,
    override val rating: Int,
    override val name: String
): Discounts {
    override fun applyToProduct(product: ProductModel, quantity: Int): Double = 0.0

    fun applyToCartList(cartList: List<CartItemModel>): Double {
        val cartItemsMap = cartList.associateBy { it.product.id }

        val numberOfSets = productIds.minOfOrNull { productId ->
            cartItemsMap[productId]?.quantity ?: 0
        } ?: 0

        if (numberOfSets <= 0) return 0.0

        val totalBundlePrice = productIds.sumOf { productId ->
            cartItemsMap[productId]?.let { it.product.price * numberOfSets } ?: 0.0
        }
        val discountAmount = totalBundlePrice - (bundlePrice * numberOfSets)
        return if (discountAmount > 0) discountAmount else 0.0
    }

}