package com.psychojean.feature.player.api.domain.detail.model

sealed class ConferenceEntity {

    data class Exist(val conference: String): ConferenceEntity()

    data object NotExist: ConferenceEntity()
}