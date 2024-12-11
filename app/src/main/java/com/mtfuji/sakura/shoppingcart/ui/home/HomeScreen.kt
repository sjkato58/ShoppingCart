package com.mtfuji.sakura.shoppingcart.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.shoppingcart.ui.theme.ShoppingCartTheme
import com.mtfuji.sakura.shoppingcart.ui.theme.dimens

@Composable
fun HomeScreen(
    onEnterClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.background
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        vertical = MaterialTheme.dimens.unit4
                    ),
                text = "Welcome to the Shop"
            )
            Button(
                modifier = Modifier,
                onClick = onEnterClicked
            ) {
                Text(
                    modifier = Modifier,
                    text = "Enter!"
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview(

) {
    ShoppingCartTheme {
        HomeScreen(
            onEnterClicked = {}
        )
    }
}