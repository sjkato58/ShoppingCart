package com.mtfuji.sakura.data.shop.sources.remote

import com.mtfuji.sakura.dataModels.shop.ApiProductModel

interface ShopRemoteDataSource {
    suspend fun loadShopData(): List<ApiProductModel>
}