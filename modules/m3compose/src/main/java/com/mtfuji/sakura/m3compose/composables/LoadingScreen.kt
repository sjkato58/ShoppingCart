package com.mtfuji.sakura.m3compose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.m3compose.theme.ShoppingCartTheme

@Composable
fun LoadingScreen(
    color: Color = MaterialTheme.colorScheme.secondary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.6f)
            ),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = color,
            trackColor = trackColor,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ShoppingCartTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            LoadingScreen()
        }
    }
}