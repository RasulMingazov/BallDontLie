package com.psychojean.feature.player.api.data.model.city

sealed class CityData {

    data class Exist(val city: String): CityData()

    data object NotExist: CityData()
}
