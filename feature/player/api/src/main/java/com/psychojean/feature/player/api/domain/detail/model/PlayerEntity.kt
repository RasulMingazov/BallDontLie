package com.psychojean.feature.player.api.domain.detail.model

data class PlayerEntity(
    val id: Int,
    val name: NameEntity,
    val position: PositionEntity,
    val height: HeightEntity,
    val weight: WeightEntity,
    val team: TeamEntity
)

