package com.mtfuji.sakura.data.shop.sources.local

import com.mtfuji.sakura.dataModels.shop.ApiProductModel

interface ShopLocalDataSource {
    suspend fun setShoppingData(productList: List<ApiProductModel>)
    suspend fun getShoppingData(): List<ApiProductModel>
}