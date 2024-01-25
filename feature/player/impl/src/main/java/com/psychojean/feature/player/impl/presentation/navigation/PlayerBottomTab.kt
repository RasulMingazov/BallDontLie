package com.psychojean.feature.player.impl.presentation.navigation

import com.psychojean.core.impl.presentation.navigation.BottomTab
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.list.PlayersListNavigationRoute

object PlayerBottomTab: BottomTab {

    override val icon: Int = R.drawable.ic_circle

    override val route: String = PlayersListNavigationRoute.route

    override val title: Int = R.string.players
}