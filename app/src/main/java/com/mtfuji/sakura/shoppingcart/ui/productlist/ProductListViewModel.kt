package com.mtfuji.sakura.shoppingcart.ui.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtfuji.sakura.domain.shop.GetProductListUseCase
import com.mtfuji.sakura.utilities.Outcome
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
): ViewModel() {

    val uiState: StateFlow<ProductListUiState> = getProductListUseCase.getProducts()
        .map { outcome ->
            when (outcome) {
                is Outcome.Success -> ProductListUiState.Success(outcome.data)
                is Outcome.Error -> ProductListUiState.Error(outcome.exception)
            }
        }
        .onStart {
            ProductListUiState.Loading
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductListUiState.Loading
        )


}