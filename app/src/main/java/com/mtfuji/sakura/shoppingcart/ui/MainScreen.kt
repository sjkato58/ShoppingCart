package com.mtfuji.sakura.shoppingcart.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mtfuji.sakura.m3compose.theme.ShoppingCartTheme
import com.mtfuji.sakura.shoppingcart.navigation.ShopNavHost

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
            modifier = Modifier,
            topBar = {
                MainTopAppBar(
                    appState = appState,
                    onNavigationClick = {
                        appState.navigateBack()
                    }
                )
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    appState: ShopAppState,
    onNavigationClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = appState.getCurrentTitle()
            )
        },
        navigationIcon = {
            appState.currentDestinationNavData?.navIcon?.let { navIcon ->
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    )
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