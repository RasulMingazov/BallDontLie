package com.psychojean.feature.player.api.domain.detail.model

data class PlayerEntity(
    val id: Int,
    val firstName: String?,
    val lastName: String?,
    val heightFeet: Int?,
    val heightInches: Int?,
    val weightPounds: Int?,
    val position: String?,
    val team: TeamEntity?
)