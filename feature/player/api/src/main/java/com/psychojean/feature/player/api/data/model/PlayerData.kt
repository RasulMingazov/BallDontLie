package com.psychojean.feature.player.api.data.model

data class PlayerData(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val team: TeamData,
    val heightFeet: Int,
    val heightInches: Int,
    val weightPounds: Int,
)