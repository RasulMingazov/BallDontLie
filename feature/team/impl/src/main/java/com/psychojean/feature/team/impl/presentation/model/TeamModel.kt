package com.psychojean.feature.team.impl.presentation.model

internal data class TeamModel(
    val id: Int,
    val name: String,
    val division: String,
    val abbreviation: String,
    val conference: String,
    val city: String
)
