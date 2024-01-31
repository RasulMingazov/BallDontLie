package com.psychojean.feature.player.api.domain.detail.model

sealed class HeightEntity {

    data class Exist(
        val heightCm: Int,
        val heightFeet: Int
    ): HeightEntity()

    data object NotExist: HeightEntity()
}