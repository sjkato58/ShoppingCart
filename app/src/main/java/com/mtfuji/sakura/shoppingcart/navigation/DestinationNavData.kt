package com.mtfuji.sakura.shoppingcart.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.mtfuji.sakura.features.productlist.ProductListRoute
import com.mtfuji.sakura.home.HomeRoute
import com.mtfuji.sakura.shoppingcart.R
import kotlin.reflect.KClass

enum class DestinationNavData(
    @StringRes val titleTextId: Int,
    val navIcon: ImageVector?,
    val route: KClass<*>
) {
    HOME(
        titleTextId = R.string.nav_data_home,
        navIcon = null,
        route = HomeRoute::class
    ),
    PRODUCT_LIST(
        titleTextId = R.string.nav_data_product_list,
        navIcon = Icons.AutoMirrored.Filled.ArrowBack,
        route = ProductListRoute::class
    )
}

fun NavDestination?.getCurrentNavDestination(): DestinationNavData? =
    DestinationNavData.entries.firstOrNull {destinationNavData ->
        this?.hasRoute(route = destinationNavData.route) == true
    }

fun DestinationNavData?.getNavIcon(): ImageVector? = this?.navIcon