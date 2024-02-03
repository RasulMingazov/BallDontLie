package com.psychojean.feature.player.api.domain.detail.model

data class TeamEntity(
    val id: Int,
    val name: String,
    val division: String,
    val abbreviation: String,
    val conference: String,
    val city: String
)

