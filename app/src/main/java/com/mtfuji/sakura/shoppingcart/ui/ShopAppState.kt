package com.mtfuji.sakura.shoppingcart.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberShopAppState(
    navController: NavHostController = rememberNavController(),
): ShopAppState {
    return remember(
        navController
    ) {
        ShopAppState(
            navController
        )
    }
}

class ShopAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

}