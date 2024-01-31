package com.psychojean.feature.player.api.domain.detail.model

sealed class CityEntity {

    data class Exist(val city: String): CityEntity()

    data object NotExist: CityEntity()
}