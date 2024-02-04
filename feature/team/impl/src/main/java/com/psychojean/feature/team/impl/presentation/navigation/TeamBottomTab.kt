package com.psychojean.feature.team.impl.presentation.navigation

import com.psychojean.core.impl.presentation.navigation.BottomTab
import com.psychojean.feature.team.impl.R
import com.psychojean.feature.team.impl.presentation.list.TeamsListNavigationRoute
import com.psychojean.core.impl.R as coreR

object TeamBottomTab: BottomTab {

    override val icon: Int = coreR.drawable.ic_circle

    override val route: String = TeamsListNavigationRoute.route

    override val title: Int = R.string.teams
}