package com.mtfuji.sakura.data.shop

import com.mtfuji.sakura.dataModels.shop.ApiProductModel

interface ShoppingItemsRepository {
    suspend fun getProducts(): List<ApiProductModel>
    suspend fun fetchProducts():List<ApiProductModel>
}