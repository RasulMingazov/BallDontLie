package com.psychojean.feature.player.api.data.model.division

sealed class DivisionData {

    data class Exist(val division: String): DivisionData()

    data object NotExist: DivisionData()
}