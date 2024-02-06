package com.psychojean.feature.player.impl.presentation.detail.state

internal sealed class PlayerDetailEvent {
    data object Dismiss: PlayerDetailEvent()
}