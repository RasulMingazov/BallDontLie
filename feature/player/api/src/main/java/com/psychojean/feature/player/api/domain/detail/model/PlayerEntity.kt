package com.psychojean.feature.player.api.domain.detail.model

import com.psychojean.feature.team.api.domain.model.TeamEntity

data class PlayerEntity(
    val id: Int,
    val name: String,
    val position: PositionEntity,
    val height: HeightEntity,
    val weight: WeightEntity,
    val team: TeamEntity
)
