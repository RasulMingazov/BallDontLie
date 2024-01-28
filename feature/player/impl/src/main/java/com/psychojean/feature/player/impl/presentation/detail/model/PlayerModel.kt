package com.psychojean.feature.player.impl.presentation.detail.model

data class PlayerModel(
    val id: Int,
    val fullName: String,
    val height: String,
    val weight: String,
    val position: String,
    val team: PlayerTeamModel
)