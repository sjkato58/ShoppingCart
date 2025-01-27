package com.mtfuji.sakura.features.productlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.domainmodels.shop.ProductModel
import com.mtfuji.sakura.m3compose.composables.LoadingScreen
import com.mtfuji.sakura.m3compose.model.UiState
import com.mtfuji.sakura.m3compose.theme.ShoppingCartTheme
import com.mtfuji.sakura.m3compose.theme.dimens

@Composable
fun ProductsListScreen(
    uiState: UiState<List<ProductModel>>
) {
    when (uiState) {
        is UiState.Loading -> {
            ProductListLoadingScreen()
        }
        is UiState.Success -> {
            ProductListProductsScreen(
                productList = uiState.data
            )
        }
        is UiState.Error -> {

        }
    }
}

@Composable
fun ProductListLoadingScreen(

) {
    LoadingScreen()
}

@Composable
fun ProductListProductsScreen(
    productList: List<ProductModel>
) {
    LazyColumn (
        modifier = Modifier
    ) {
        items(productList) { product ->
            ProductListItem(product)
        }
    }
}

@Composable
fun ProductListItem(
    product: ProductModel
) {
    Text(
        text = product.name,
        modifier = Modifier
            .padding(MaterialTheme.dimens.unit4)
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Preview
@Composable
private fun ProductListScreenLoadingPreview() {
    ShoppingCartTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProductsListScreen(
                uiState = UiState.Loading
            )
        }
    }
}