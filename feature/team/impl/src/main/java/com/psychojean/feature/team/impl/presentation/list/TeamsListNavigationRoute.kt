package com.psychojean.feature.team.impl.presentation.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.psychojean.core.impl.presentation.navigation.NavigationRoute

internal object TeamsListNavigationRoute : NavigationRoute {

    override val route: String = "teams"

    override fun register(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(route) {
            TeamsListScreen(modifier = modifier)
        }
    }
}