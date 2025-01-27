package com.mtfuji.sakura.data.shop

import com.mtfuji.sakura.dataModels.shop.ApiProductModel

interface ShopRepository {
    suspend fun getProducts(): List<ApiProductModel>
    suspend fun fetchProducts():List<ApiProductModel>
}