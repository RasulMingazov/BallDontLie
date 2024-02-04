package com.psychojean.feature.team.api.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamsRemote(
    @Json(name = "data") val teams: List<TeamRemote>?
)