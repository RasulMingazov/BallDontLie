package com.psychojean.feature.player.api.data.model.height

sealed class HeightData {

    data class Exist(
        val heightCm: Int,
        val heightFeet: Int
    ): HeightData()

    data object NotExist: HeightData()
}