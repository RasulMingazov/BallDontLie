package com.psychojean.feature.player.api.domain.detail.model

sealed class WeightEntity {

    data class Exist(
        val weightPounds: Int,
        val weightKg: Int
    ): WeightEntity()

    data object NotExist: WeightEntity()
}