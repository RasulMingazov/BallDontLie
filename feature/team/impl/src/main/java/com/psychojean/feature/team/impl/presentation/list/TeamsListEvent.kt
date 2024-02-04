package com.psychojean.feature.team.impl.presentation.list

internal sealed class TeamsListEvent {
    data object Refresh: TeamsListEvent()
    data object EndRefresh: TeamsListEvent()
}