package com.psychojean.balldontlie.ui.bottom

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.psychojean.core.impl.presentation.navigation.BottomTab

@Composable
fun RowScope.BottomBarItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    currentRoute: String,
    tab: BottomTab
) {
    NavigationBarItem(
        modifier = modifier,
        selected = currentRoute == tab.route,
        onClick = {
            println("TEST_TAG ${currentRoute} ${tab.route}")
            if (currentRoute != tab.route)
                navController.navigate(tab.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
        },
        icon = {
            Icon(
                painter = painterResource(id = tab.icon),
                contentDescription = stringResource(id = tab.title)
            )
        },
        label = {
            Text(text = stringResource(tab.title))
        }
    )
}