package com.psychojean.feature.team.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.psychojean.core.impl.presentation.navigation.NavigationRoute
import com.psychojean.feature.team.impl.presentation.list.TeamsListNavigationRoute
import com.psychojean.feature.team.impl.presentation.starred.StarredTeamsListNavigationRoute

object TeamNavigationRoute : NavigationRoute {

    override val route: String = TeamsListNavigationRoute.route

    override fun register(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        val childRoutes = arrayListOf(TeamsListNavigationRoute, StarredTeamsListNavigationRoute)
        childRoutes.forEach { screen ->
            screen.register(
                modifier = modifier,
                navController = navController,
                navGraphBuilder = navGraphBuilder
            )
        }
    }
}
