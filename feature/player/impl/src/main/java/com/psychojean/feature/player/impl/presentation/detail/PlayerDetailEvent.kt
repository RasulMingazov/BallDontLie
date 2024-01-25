package com.psychojean.feature.player.impl.presentation.detail

sealed class PlayerDetailEvent {
    data object Dismiss: PlayerDetailEvent()
}