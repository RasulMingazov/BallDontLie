package com.psychojean.feature.player.impl.data.remote.model

import com.psychojean.feature.team.api.data.remote.model.TeamRemote
import com.squareup.moshi.Json

internal data class PlayerRemote(
    @Json(name = "id") val id: Int,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "position") val position: String?,
    @Json(name = "team") val team: TeamRemote,
    @Json(name = "height_feet") val heightFeet: Int?,
    @Json(name = "weight_pounds") val weightPounds: Int?
)