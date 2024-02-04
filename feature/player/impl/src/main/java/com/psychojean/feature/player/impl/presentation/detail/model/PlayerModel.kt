package com.psychojean.feature.player.impl.presentation.detail.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class PlayerModel(
    val id: Int,
    val fullName: String,
    val height: String,
    val weight: String,
    val position: String,
    val teamId: Int,
    val teamName: String
)