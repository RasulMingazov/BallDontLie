package com.psychojean.feature.player.api.data.model

data class TeamData(
    val id: Int,
    val name: String?,
    val fullName: String?,
    val division: String?,
    val abbreviation: String?,
    val conference: String?,
    val city: String?
)