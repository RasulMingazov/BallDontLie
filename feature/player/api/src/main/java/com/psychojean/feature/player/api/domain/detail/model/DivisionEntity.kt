package com.psychojean.feature.player.api.domain.detail.model

sealed class DivisionEntity {

    data class Exist(val division: String): DivisionEntity()

    data object NotExist: DivisionEntity()
}
