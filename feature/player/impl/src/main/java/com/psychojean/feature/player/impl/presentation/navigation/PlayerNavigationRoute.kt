package com.psychojean.feature.player.impl.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.psychojean.core.impl.presentation.navigation.NavigationRoute
import com.psychojean.feature.player.impl.presentation.detail.PlayerDetailNavigationRoute
import com.psychojean.feature.player.impl.presentation.list.PlayersListNavigationRoute

object PlayerNavigationRoute : NavigationRoute {

    override val route: String = "playerGraph"

    override fun register(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        val childRoutes = listOf(PlayersListNavigationRoute, PlayerDetailNavigationRoute)
        navGraphBuilder.navigation(route = route, startDestination = PlayersListNavigationRoute.route) {
            childRoutes.forEach { screen ->
                screen.register(
                    modifier = modifier,
                    navController = navController,
                    navGraphBuilder = this
                )
            }
        }
    }
}
