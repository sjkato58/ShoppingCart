package com.mtfuji.sakura.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable object HomeRoute

fun NavGraphBuilder.homeRoute(
    onEnterClicked: () -> Unit
) {
    composable<HomeRoute> {
        HomeRoute(onEnterClicked)
    }
}

@Composable
fun HomeRoute(
    onEnterClicked: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    HomeScreen(
        onEnterClicked = onEnterClicked
    )
}