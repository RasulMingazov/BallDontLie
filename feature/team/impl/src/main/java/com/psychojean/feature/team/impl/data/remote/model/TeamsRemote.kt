package com.psychojean.feature.team.impl.data.remote.model

import com.psychojean.feature.team.api.data.remote.model.TeamRemote
import com.squareup.moshi.Json

internal data class TeamsRemote(
    @Json(name = "data") val teams: List<TeamRemote>?
)