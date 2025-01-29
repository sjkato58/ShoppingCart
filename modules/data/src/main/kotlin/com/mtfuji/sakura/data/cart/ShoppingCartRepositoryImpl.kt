package com.mtfuji.sakura.data.cart

import com.mtfuji.sakura.data.cart.sources.local.ShoppingCartLocalDataSource
import com.mtfuji.sakura.dataModels.cart.ApiCartItemModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class ShoppingCartRepositoryImpl(
    private val dispatcherProvider: DispatcherProvider,
    private val shoppingCartLocalDataSource: ShoppingCartLocalDataSource
): ShoppingCartRepository {
    override suspend fun setShoppingCartItems(cartList: List<ApiCartItemModel>) {
        withContext(dispatcherProvider.io) {
            shoppingCartLocalDataSource.setShoppingCartData(cartList)
        }
    }

    override suspend fun getShoppingCartItems(): Flow<List<ApiCartItemModel>> {
        return withContext(dispatcherProvider.io) {
            shoppingCartLocalDataSource.getShoppingCartData()
        }
    }

    override suspend fun modifyShoppingCartItem(cartItemModel: ApiCartItemModel) {
        withContext(dispatcherProvider.io) {

        }
    }

    override suspend fun getTotalCost(): Double {
        return withContext(dispatcherProvider.io) {
            shoppingCartLocalDataSource.getShoppingCartData().first().sumOf { it.product.price * it.quantity }
        }
    }
}