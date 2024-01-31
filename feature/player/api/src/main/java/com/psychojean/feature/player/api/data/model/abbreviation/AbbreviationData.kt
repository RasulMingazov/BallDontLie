package com.psychojean.feature.player.api.data.model.abbreviation

sealed class AbbreviationData {

    data class Exist(val abbreviation: String): AbbreviationData()

    data object NotExist: AbbreviationData()
}