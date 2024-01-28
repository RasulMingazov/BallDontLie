package com.psychojean.balldontlie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.psychojean.balldontlie.ui.RoutesContainer
import com.psychojean.balldontlie.ui.bottom.BallNavigationBar
import com.psychojean.balldontlie.ui.theme.BallDontLieTheme
import com.psychojean.core.impl.presentation.navigation.BottomTab
import com.psychojean.core.impl.presentation.navigation.NavigationRoute
import com.psychojean.feature.player.impl.presentation.navigation.PlayerBottomTab
import com.psychojean.feature.player.impl.presentation.navigation.PlayerNavigationRoute
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            BallDontLieTheme {
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navController = rememberNavController(bottomSheetNavigator)
                val childRoutes = listOf<NavigationRoute>(PlayerNavigationRoute)
                val bottomTabs = listOf<BottomTab>(PlayerBottomTab)
                val modifier = Modifier
                Scaffold(
                    bottomBar = {
                        BallNavigationBar(
                            modifier = modifier,
                            bottomTabs = bottomTabs,
                            navController = navController
                        )
                    },
                    content = {
                        Box(
                            modifier = modifier.background(MaterialTheme.colorScheme.background)
                        ) {
                            RoutesContainer(
                                modifier = modifier,
                                navController = navController,
                                bottomSheetNavigator = bottomSheetNavigator,
                                routes = childRoutes
                            )
                        }
                    }
                )
            }
        }
    }
}