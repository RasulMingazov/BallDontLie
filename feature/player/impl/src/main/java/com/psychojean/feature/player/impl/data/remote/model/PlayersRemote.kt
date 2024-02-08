package com.psychojean.feature.player.impl.data.remote.model

import com.squareup.moshi.Json


data class PlayersRemote(
    @Json(name = "data") val players: List<PlayerRemote>?
)