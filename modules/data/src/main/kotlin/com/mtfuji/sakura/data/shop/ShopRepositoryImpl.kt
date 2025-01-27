package com.mtfuji.sakura.data.shop

import com.mtfuji.sakura.data.shop.sources.local.ShopLocalDataSource
import com.mtfuji.sakura.data.shop.sources.remote.ShopRemoteDataSource
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.dataModels.shop.ApiProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class ShopRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val localDataSource: ShopLocalDataSource,
    private val remoteDataSource: ShopRemoteDataSource
): ShopRepository {
    override suspend fun getProducts(): List<ApiProductModel> {
        return withContext(dispatcherProvider.io) {
            val items = localDataSource.getShoppingData()
            items.ifEmpty {
                fetchProducts()
            }
        }
    }

    override suspend fun fetchProducts(): List<ApiProductModel> {
        return withContext(dispatcherProvider.io) {
            val response = remoteDataSource.loadShopData()
            if (response.isNotEmpty()) {
                localDataSource.setShoppingData(response)
                response
            } else {
                throw Exception("No Items!")
            }
        }
    }
}