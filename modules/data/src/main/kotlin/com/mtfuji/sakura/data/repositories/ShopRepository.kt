package com.mtfuji.sakura.data.repositories

import com.mtfuji.sakura.dataModels.ProductModel

interface ShopRepository {
    suspend fun setProducts(productList: List<ProductModel>)
    suspend fun getProducts(): List<ProductModel>
}