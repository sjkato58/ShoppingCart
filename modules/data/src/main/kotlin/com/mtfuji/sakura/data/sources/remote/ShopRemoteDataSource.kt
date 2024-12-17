package com.mtfuji.sakura.data.sources.remote

import com.mtfuji.sakura.dataModels.ProductModel

interface ShopRemoteDataSource {
    suspend fun loadShopData(): List<ProductModel>
}