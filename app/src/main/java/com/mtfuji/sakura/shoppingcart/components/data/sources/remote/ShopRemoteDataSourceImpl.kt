package com.mtfuji.sakura.shoppingcart.components.data.sources.remote

import com.mtfuji.sakura.data.sources.remote.ShopRemoteDataSource
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.firebase.FirebaseConfigManager
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ShopRemoteDataSourceImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseConfigManager: FirebaseConfigManager
): ShopRemoteDataSource {

    companion object {
        const val PRODUCT_LIST_KEY = "product_list"
    }

    override suspend fun loadShopData(): List<ProductModel> = withContext(dispatcherProvider.io) {
        val productJson = firebaseConfigManager.getConfigValue(PRODUCT_LIST_KEY)
        parseProductListJSON(productJson)
    }

    private fun parseProductListJSON(jsonString: String): List<ProductModel> {
        return Json.decodeFromString(jsonString)
    }
}