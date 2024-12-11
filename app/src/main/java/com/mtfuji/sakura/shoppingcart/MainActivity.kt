package com.mtfuji.sakura.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.shoppingcart.navigation.ShopNavHost
import com.mtfuji.sakura.shoppingcart.ui.ShopAppState
import com.mtfuji.sakura.shoppingcart.ui.rememberShopAppState
import com.mtfuji.sakura.shoppingcart.ui.theme.ShoppingCartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                val appState = rememberShopAppState()
                MainScreen(
                    appState = appState
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    appState: ShopAppState
) {
    Surface(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        Scaffold(
            modifier = Modifier
        ) { paddingValues ->
            ShopNavHost(
                appState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingCartTheme {
        val appState = rememberShopAppState()
        MainScreen(
            appState = appState
        )
    }
}