package com.mtfuji.sakura.data.repositories

import com.mtfuji.sakura.dataModels.CartItemModel
import com.mtfuji.sakura.dataModels.ProductModel

interface CartRepository {
    suspend fun addProduct(productModel: ProductModel, quantity: Int = 1)
    suspend fun removeProduct(productModel: ProductModel)
    suspend fun reduceProductQuantity(productModel: ProductModel, quantity: Int = 1)
    suspend fun getTotalCost(): Double
    suspend fun getItems(): List<CartItemModel>
}