package com.mtfuji.sakura.data.cart.sources.local

import com.mtfuji.sakura.dataModels.cart.ApiCartItemModel
import com.mtfuji.sakura.utilities.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class ShoppingCartLocalDataSourceImpl(
    private val dispatcherProvider: DispatcherProvider
): ShoppingCartLocalDataSource {
    private val _items = MutableStateFlow<List<ApiCartItemModel>>(emptyList())
    private val items: StateFlow<List<ApiCartItemModel>> = _items.asStateFlow()

    override suspend fun setShoppingCartData(productList: List<ApiCartItemModel>) {
        withContext(dispatcherProvider.io) {
            _items.value = productList
        }
    }

    override suspend fun getShoppingCartData(): Flow<List<ApiCartItemModel>>
        = withContext(dispatcherProvider.io) {
            items
        }

    override suspend fun updateShoppingCartItem(cartItem: ApiCartItemModel) {
        withContext(dispatcherProvider.io) {
            _items.value.map {
                if (it.product.id == cartItem.product.id) {
                    cartItem
                } else it
            }
        }
    }
}