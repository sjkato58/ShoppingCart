package com.mtfuji.sakura.features.productlist

import com.mtfuji.sakura.dataModels.ProductModel

sealed interface ProductListUiState {
    data object Loading : ProductListUiState
    data class Success(
        val productList: List<ProductModel>
    ): ProductListUiState
    data class Error(val throwable: Throwable) : ProductListUiState
}
