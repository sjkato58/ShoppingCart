package com.mtfuji.sakura.features.productlist.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mtfuji.sakura.domainmodels.shop.ProductModel
import com.mtfuji.sakura.m3compose.theme.dimens

@Composable
fun ProductsListCard(
    item: ProductModel,
    onItemSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.unit3)
            .clickable { onItemSelected(item.id) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {

    }
}