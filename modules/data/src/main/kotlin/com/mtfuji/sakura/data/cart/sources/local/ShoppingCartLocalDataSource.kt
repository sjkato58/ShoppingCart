package com.mtfuji.sakura.data.cart.sources.local

import com.mtfuji.sakura.dataModels.cart.ApiCartItemModel
import kotlinx.coroutines.flow.Flow

interface ShoppingCartLocalDataSource {
    suspend fun setShoppingCartData(productList: List<ApiCartItemModel>)
    suspend fun getShoppingCartData(): Flow<List<ApiCartItemModel>>
    suspend fun updateShoppingCartItem(cartItem: ApiCartItemModel)
}