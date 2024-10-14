package com.mtfuji.sakura.domain.discounts

import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.models.ProductModel

interface Discounts {
    val rating: Int
    val name: String
    fun applyToProduct(product: ProductModel, quantity: Int): Double
}