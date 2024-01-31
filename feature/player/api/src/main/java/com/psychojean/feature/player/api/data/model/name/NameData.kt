package com.psychojean.feature.player.api.data.model.name

sealed class NameData {

    data class Exist(val fullName: String): NameData()

    data object NotExist: NameData()
}