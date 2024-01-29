package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.PlayerData
import com.psychojean.feature.player.api.data.model.TeamData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote

internal class DefaultPlayerRemoteToDataMapper : PlayerRemoteToDataMapper {

    override fun map(item: PlayerRemote): PlayerData = with(item) {
        PlayerData(
            id,
            firstName,
            lastName,
            position,
            team?.let { team ->
                TeamData(
                    team.id,
                    team.name,
                    team.fullName,
                    team.division,
                    team.abbreviation,
                    team.conference,
                    team.city
                )
            },
            heightFeet,
            heightInches,
            weightPounds
        )
    }
}