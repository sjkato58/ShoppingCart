package com.mtfuji.sakura.shoppingcart.ui.productlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable object ProductListRoute

fun NavGraphBuilder.productListRoute(

) {
    composable<ProductListRoute> {
        ProductListRoute()
    }
}

@Composable
fun ProductListRoute(
    viewModel: ProductListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductsListScreen(
        uiState
    )
}