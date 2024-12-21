package com.mtfuji.sakura.shoppingcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mtfuji.sakura.m3compose.theme.ShoppingCartTheme
import com.mtfuji.sakura.shoppingcart.ui.MainScreen
import com.mtfuji.sakura.shoppingcart.ui.rememberShopAppState

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