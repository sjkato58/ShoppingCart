package com.mtfuji.sakura.data.repositories

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class ShopRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider
): ShopRepository {
    private val items: MutableList<ProductModel> = mutableListOf()

    override suspend fun setProducts(productList: List<ProductModel>) {
        withContext(dispatcherProvider.io) {
            items.addAll(productList)
        }
    }

    override suspend fun getProducts(): List<ProductModel>
        = withContext(dispatcherProvider.default) {
            items
        }
}