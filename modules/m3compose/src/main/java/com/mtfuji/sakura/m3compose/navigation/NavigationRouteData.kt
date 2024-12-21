package com.mtfuji.sakura.m3compose.navigation

import androidx.annotation.StringRes
import kotlin.reflect.KClass

interface NavigationRouteData {
    @get:StringRes val titleTextId: Int
    val route: KClass<*>
}