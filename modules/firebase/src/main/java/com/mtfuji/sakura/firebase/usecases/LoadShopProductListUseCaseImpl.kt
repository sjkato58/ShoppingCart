package com.mtfuji.sakura.firebase.usecases

import android.util.Log
import com.mtfuji.sakura.data.repositories.ShopRepository
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.firebase.FirebaseConfigManager
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class LoadShopProductListUseCaseImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val firebaseConfigManager: FirebaseConfigManager,
    private val shopRepository: ShopRepository
): LoadShopProductListUseCase {

    companion object {
        const val PRODUCT_LIST_KEY = "product_list"
    }

    override suspend fun loadProductList() {
        withContext(dispatcherProvider.default) {
            val productJson = firebaseConfigManager.getConfigValue(PRODUCT_LIST_KEY)
            val productList = parseProductListJSON(productJson)
            Log.e("seiji", "LoadShopProductListUseCaseImpl - loadProductList: $productList")
            shopRepository.setProducts(productList)
        }
    }

    private fun parseProductListJSON(jsonString: String): List<ProductModel> {
        return Json.decodeFromString(jsonString)
    }
}