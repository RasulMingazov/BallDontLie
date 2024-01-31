package com.psychojean.feature.player.api.data.model.weight

sealed class WeightData {

    data class Exist(
        val weightPounds: Int,
        val weightKg: Int
    ): WeightData()

    data object NotExist: WeightData()
}
