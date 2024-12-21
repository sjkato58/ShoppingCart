package com.mtfuji.sakura.shoppingcart.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mtfuji.sakura.shoppingcart.navigation.DestinationNavData
import com.mtfuji.sakura.shoppingcart.navigation.getCurrentNavDestination

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

    val currentDestinationNavData: DestinationNavData?
        @Composable get() = currentDestination.getCurrentNavDestination()

    @Composable
    fun getCurrentTitle(): String = currentDestinationNavData?.let {
        stringResource(it.titleTextId)
    } ?: ""

    fun navigateBack() = navController.navigateUp()
}