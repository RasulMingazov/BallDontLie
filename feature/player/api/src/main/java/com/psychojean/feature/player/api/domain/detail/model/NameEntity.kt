package com.psychojean.feature.player.api.domain.detail.model

sealed class NameEntity {

    data class Exist(val fullName: String): NameEntity()

    data object NotExist: NameEntity()
}