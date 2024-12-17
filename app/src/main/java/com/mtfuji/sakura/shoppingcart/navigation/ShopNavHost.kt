package com.mtfuji.sakura.shoppingcart.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mtfuji.sakura.shoppingcart.ui.ShopAppState
import com.mtfuji.sakura.shoppingcart.ui.home.HomeRoute
import com.mtfuji.sakura.shoppingcart.ui.home.homeRoute
import com.mtfuji.sakura.shoppingcart.ui.productlist.ProductListRoute
import com.mtfuji.sakura.shoppingcart.ui.productlist.productListRoute

@Composable
fun ShopNavHost(
    appState: ShopAppState,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        homeRoute {
            navController.navigate(ProductListRoute)
        }
        productListRoute()
    }
}