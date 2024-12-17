package com.mtfuji.sakura.data.repositories

import com.mtfuji.sakura.data.sources.local.ShopLocalDataSource
import com.mtfuji.sakura.data.sources.remote.ShopRemoteDataSource
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class ShopRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val localDataSource: ShopLocalDataSource,
    private val remoteDataSource: ShopRemoteDataSource
): ShopRepository {
    override suspend fun setProducts(productList: List<ProductModel>) {
        withContext(dispatcherProvider.io) {
            localDataSource.setShoppingData(productList)
        }
    }

    override suspend fun getProducts(): List<ProductModel> {
        return withContext(dispatcherProvider.io) {
            val items = localDataSource.getShoppingData()
            items.ifEmpty {
                val response = remoteDataSource.loadShopData()
                println("ShopRepositoryImpl - getProducts: $response")
                if (response.isNotEmpty()) {
                    localDataSource.setShoppingData(response)
                    response
                } else {
                    throw Exception("No Items!")
                }
            }
        }
    }
}