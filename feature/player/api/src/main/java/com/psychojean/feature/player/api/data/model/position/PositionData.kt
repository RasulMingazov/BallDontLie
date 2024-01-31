package com.psychojean.feature.player.api.data.model.position

sealed class PositionData {

    data class Exist(val position: String): PositionData()

    data object NotExist: PositionData()
}
