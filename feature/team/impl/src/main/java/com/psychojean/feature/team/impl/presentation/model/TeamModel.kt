package com.psychojean.feature.team.impl.presentation.model

import androidx.compose.runtime.Stable

@Stable
internal data class TeamModel(
    val id: Int,
    val name: String,
    val division: String,
    val abbreviation: String,
    val conference: String,
    val city: String,
    var isStarred: Boolean
)
