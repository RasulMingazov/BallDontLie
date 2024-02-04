package com.psychojean.balldontlie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.psychojean.balldontlie.ui.RoutesContainer
import com.psychojean.balldontlie.ui.bottom.BallNavigationBar
import com.psychojean.balldontlie.ui.theme.BallDontLieTheme
import com.psychojean.feature.player.impl.presentation.navigation.PlayerBottomTab
import com.psychojean.feature.player.impl.presentation.navigation.PlayerNavigationRoute
import com.psychojean.feature.team.impl.presentation.navigation.TeamBottomTab
import com.psychojean.feature.team.impl.presentation.navigation.TeamNavigationRoute
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

                val childRoutes = arrayListOf(PlayerNavigationRoute, TeamNavigationRoute)
                val bottomTabs = arrayListOf(PlayerBottomTab, TeamBottomTab)

                Scaffold(
                    bottomBar = {
                        BallNavigationBar(
                            bottomTabs = bottomTabs,
                            navController = navController
                        )
                    },
                    content = { padding ->
                        Box(
                            modifier = Modifier
                                .padding(
                                    bottom = padding.calculateBottomPadding(),
                                    start = padding.calculateStartPadding(LayoutDirection.Ltr),
                                    end = padding.calculateEndPadding(LayoutDirection.Rtl)
                                )
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            RoutesContainer(
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