package com.psychojean.feature.team.api.domain.model

data class TeamEntity(
    val id: Int,
    val name: String,
    val division: String,
    val abbreviation: String,
    val conference: String,
    val city: String,
    val isStarred: Boolean,
)

