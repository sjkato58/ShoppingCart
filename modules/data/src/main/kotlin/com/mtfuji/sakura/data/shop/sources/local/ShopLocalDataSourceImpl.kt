package com.mtfuji.sakura.data.shop.sources.local

import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.dataModels.shop.ApiProductModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext

class ShopLocalDataSourceImpl(
    private val dispatcherProvider: DispatcherProvider
): ShopLocalDataSource {
    private val items: MutableList<ApiProductModel> = mutableListOf()

    override suspend fun setShoppingData(productList: List<ApiProductModel>) {
        withContext(dispatcherProvider.io) {
            items.clear()
            items.addAll(productList)
        }
    }

    override suspend fun getShoppingData(): List<ApiProductModel>
            = withContext(dispatcherProvider.io) {
                items
            }
}