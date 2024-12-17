package com.mtfuji.sakura.shoppingcart.ui.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.dataModels.ProductModel
import com.mtfuji.sakura.shoppingcart.ui.composables.LoadingScreen
import com.mtfuji.sakura.shoppingcart.ui.theme.ShoppingCartTheme
import com.mtfuji.sakura.shoppingcart.ui.theme.dimens

@Composable
fun ProductsListScreen(
    uiState: ProductListUiState
) {
    when (uiState) {
        is ProductListUiState.Loading -> {
            ProductListLoadingScreen()
        }
        is ProductListUiState.Success -> {
            ProductListProductsScreen(
                productList = uiState.productList
            )
        }
        is ProductListUiState.Error -> {

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
                .background(color = Color.White)
        ) {
            ProductsListScreen(
                uiState = ProductListUiState.Loading
            )
        }
    }
}