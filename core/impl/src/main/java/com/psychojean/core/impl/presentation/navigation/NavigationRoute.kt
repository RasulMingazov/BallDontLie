package com.psychojean.core.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationRoute {

    val route: String

    fun register(
        modifier: Modifier = Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )

}