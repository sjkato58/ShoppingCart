package com.mtfuji.sakura.data.cart

import com.mtfuji.sakura.dataModels.cart.ApiCartItemModel
import kotlinx.coroutines.flow.Flow

interface ShoppingCartRepository {
    suspend fun setShoppingCartItems(cartList: List<ApiCartItemModel>)
    suspend fun getShoppingCartItems(): Flow<List<ApiCartItemModel>>
    suspend fun modifyShoppingCartItem(cartItemModel: ApiCartItemModel)
    suspend fun getTotalCost(): Double
}