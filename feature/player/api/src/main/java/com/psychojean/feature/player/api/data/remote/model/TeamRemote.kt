package com.psychojean.feature.player.api.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamRemote(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "full_name") val fullName: String?,
    @Json(name = "division") val division: String?,
    @Json(name = "abbreviation") val abbreviation: String?,
    @Json(name = "conference") val conference: String?,
    @Json(name = "city") val city: String?
)