package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.PlayerData
import com.psychojean.feature.player.api.data.model.TeamData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote

class DefaultPlayerRemoteToDataMapper : PlayerRemoteToDataMapper {

    override fun map(item: PlayerRemote): PlayerData = with(item) {
        PlayerData(
            id ?: -1,
            firstName ?: "",
            lastName ?: "",
            position ?: "",
            team?.let {
                TeamData(
                    team?.id ?: -1,
                    team?.name ?: "",
                    team?.fullName ?: "",
                    team?.division ?: "",
                    team?.abbreviation ?: "",
                    team?.conference ?: "",
                    team?.city ?: ""
                )
            } ?: TeamData(-1, "", "", "", "", "", ""),
            heightFeet ?: -1,
            heightInches ?: -1,
            weightPounds ?: -1
        )
    }
}