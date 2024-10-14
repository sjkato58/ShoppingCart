package com.mtfuji.sakura.domain.repositories

import com.mtfuji.sakura.domain.models.CartItemModel
import com.mtfuji.sakura.domain.models.ProductModel

interface CartRepository {
    suspend fun addProduct(productModel: ProductModel, quantity: Int = 1)
    suspend fun removeProduct(productModel: ProductModel)
    suspend fun reduceProductQuantity(productModel: ProductModel, quantity: Int = 1)
    suspend fun getTotalCost(): Double
    suspend fun getItems(): List<CartItemModel>
}