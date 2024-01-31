package com.psychojean.feature.player.api.domain.detail.model

sealed class PositionEntity {

    data class Exist(val position: String): PositionEntity()

    data object NotExist: PositionEntity()
}