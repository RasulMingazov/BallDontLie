package com.psychojean.feature.player.impl.presentation.detail

internal sealed class PlayerDetailEvent {
    data object Dismiss: PlayerDetailEvent()
}