package com.psychojean.feature.player.impl.data.remote.model

import com.psychojean.feature.team.api.data.remote.model.TeamRemote

data class PlayerRemote(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val position: String?,
    val team: TeamRemote,
    val heightFeet: Int?,
    val weightPounds: Int?
)