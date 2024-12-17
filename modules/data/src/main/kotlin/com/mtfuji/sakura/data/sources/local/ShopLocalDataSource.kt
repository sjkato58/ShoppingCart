package com.mtfuji.sakura.data.sources.local

import com.mtfuji.sakura.dataModels.ProductModel

interface ShopLocalDataSource {
    suspend fun setShoppingData(productList: List<ProductModel>)
    suspend fun getShoppingData(): List<ProductModel>
}