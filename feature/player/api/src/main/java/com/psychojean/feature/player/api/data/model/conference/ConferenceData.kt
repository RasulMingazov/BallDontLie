package com.psychojean.feature.player.api.data.model.conference

sealed class ConferenceData {

    data class Exist(val conference: String): ConferenceData()

    data object NotExist: ConferenceData()
}