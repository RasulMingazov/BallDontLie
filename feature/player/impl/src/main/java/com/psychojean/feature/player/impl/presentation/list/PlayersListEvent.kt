package com.psychojean.feature.player.impl.presentation.list

internal sealed class PlayersListEvent {
    data object Retry: PlayersListEvent()
    data object Refresh: PlayersListEvent()
    data object EndRefresh: PlayersListEvent()
}