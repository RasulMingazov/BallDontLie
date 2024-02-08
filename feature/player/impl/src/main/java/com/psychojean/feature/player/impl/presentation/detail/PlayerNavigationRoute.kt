package com.psychojean.feature.player.impl.presentation.detail

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.psychojean.core.impl.presentation.navigation.NavigationRoute

internal object PlayerNavigationRoute : NavigationRoute {

    const val idArgumentKey = "id"

    override val route = "player/{$idArgumentKey}"

    fun route(id: Int) = "player/$id"

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun register(
        modifier: Modifier,
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.bottomSheet(
            route = route,
            arguments = listOf(
                navArgument(idArgumentKey) {
                    type = NavType.IntType
                }
            )
        ) {
            PlayerScreen(
                modifier = modifier,
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
