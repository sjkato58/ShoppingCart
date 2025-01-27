package com.mtfuji.sakura.features.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mtfuji.sakura.domain.shop.usecases.GetProductListUseCase
import com.mtfuji.sakura.domainmodels.shop.ProductModel
import com.mtfuji.sakura.m3compose.model.UiState
import com.mtfuji.sakura.utilities.Outcome
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProductListViewModel(
    private val getProductListUseCase: GetProductListUseCase
): ViewModel() {

    val uiState: StateFlow<UiState<List<ProductModel>>> = observeProductList()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )

    private fun observeProductList(): Flow<UiState<List<ProductModel>>> {
        return getProductListUseCase.getProducts()
            .map { outcome ->
                when (outcome) {
                    is Outcome.Success -> UiState.Success(outcome.data)
                    is Outcome.Error -> UiState.Error(outcome.exception)
                }
            }
            .catch { emit(UiState.Error(it)) }
    }
}