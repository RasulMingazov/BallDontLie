package com.psychojean.feature.player.api.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayersRemote(
    @Json(name = "data") val players: List<PlayerRemote>?
)