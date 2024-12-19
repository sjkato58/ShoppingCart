package com.mtfuji.sakura.data.repositories

import com.mtfuji.sakura.dataModels.ProductModel

interface ShopRepository {
    suspend fun getProducts(): List<ProductModel>
    suspend fun refreshProducts():List<ProductModel>
}