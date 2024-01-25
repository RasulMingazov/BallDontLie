package com.psychojean.balldontlie.ui.bottom

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.psychojean.core.impl.presentation.navigation.BottomTab

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    bottomTabs: List<BottomTab> = emptyList(),
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: bottomTabs.first().route

    NavigationBar(modifier = Modifier) {
        bottomTabs.forEach { tab ->
            BottomBarItem(
                modifier = modifier,
                navController = navController,
                currentRoute = currentRoute,
                tab = tab
            )
        }
    }
}