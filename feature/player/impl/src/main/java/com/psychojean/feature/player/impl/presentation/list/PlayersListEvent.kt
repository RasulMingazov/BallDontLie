package com.psychojean.feature.player.impl.presentation.list

internal sealed class PlayersListEvent {
    data object Retry: PlayersListEvent()
}