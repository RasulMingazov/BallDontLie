package com.psychojean.balldontlie.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.psychojean.core.impl.presentation.navigation.NavigationRoute

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun RoutesContainer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    bottomSheetNavigator: BottomSheetNavigator,
    routes: List<NavigationRoute> = emptyList()
) {
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = routes.first().route
        ) {
            routes.forEach { route ->
                route.register(
                    modifier = modifier,
                    navGraphBuilder = this,
                    navController = navController
                )
            }
        }
    }
}