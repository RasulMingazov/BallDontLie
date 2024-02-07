package com.psychojean.feature.team.impl.presentation.starred

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.psychojean.core.impl.presentation.navigation.NavigationRoute

internal object StarredTeamsListNavigationRoute : NavigationRoute {

    override val route: String = "starredTeams"

    override fun register(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(route) {
            StarredTeamsListScreen(modifier = modifier)
        }
    }
}