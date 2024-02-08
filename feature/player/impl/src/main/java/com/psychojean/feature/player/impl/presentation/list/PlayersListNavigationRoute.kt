package com.psychojean.feature.player.impl.presentation.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.psychojean.core.impl.presentation.navigation.NavigationRoute
import com.psychojean.feature.player.impl.presentation.detail.PlayerNavigationRoute

internal object PlayersListNavigationRoute : NavigationRoute {

    override val route: String = "players"

    override fun register(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable(route) {
            PlayersListScreen(
                modifier = modifier,
                onDetailClick = { id -> navController.navigate(PlayerNavigationRoute.route(id)) { launchSingleTop = true } }
            )
        }
    }
}