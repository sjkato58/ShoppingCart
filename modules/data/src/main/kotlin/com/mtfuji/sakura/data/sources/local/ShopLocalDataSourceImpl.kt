package com.mtfuji.sakura.data.sources.local

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class ShopLocalDataSourceImpl(
    private val dispatcherProvider: DispatcherProvider
): ShopLocalDataSource {
    private val items: MutableList<ProductModel> = mutableListOf()

    override suspend fun setShoppingData(productList: List<ProductModel>) {
        withContext(dispatcherProvider.io) {
            items.clear()
            items.addAll(productList)
        }
    }

    override suspend fun getShoppingData(): List<ProductModel>
            = withContext(dispatcherProvider.io) {
                items
            }
}