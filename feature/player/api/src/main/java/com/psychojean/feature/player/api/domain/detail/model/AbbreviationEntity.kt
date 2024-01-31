package com.psychojean.feature.player.api.domain.detail.model

sealed class AbbreviationEntity {

    data class Exist(val division: String): AbbreviationEntity()

    data object NotExist: AbbreviationEntity()
}