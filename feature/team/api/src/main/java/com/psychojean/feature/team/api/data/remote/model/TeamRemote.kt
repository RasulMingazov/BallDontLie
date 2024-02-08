package com.psychojean.feature.team.api.data.remote.model

import com.squareup.moshi.Json

data class TeamRemote(
    @Json(name = "id") val id: Int,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "division") val division: String,
    @Json(name = "abbreviation") val abbreviation: String,
    @Json(name = "conference") val conference: String,
    @Json(name = "city") val city: String
)