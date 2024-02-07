package com.psychojean.feature.team.impl.data.remote.mapper

import com.psychojean.feature.team.api.data.model.TeamData
import com.psychojean.feature.team.api.data.remote.TeamRemoteToDataMapper
import com.psychojean.feature.team.api.data.remote.model.TeamRemote

internal object DefaultTeamRemoteToDataMapper : TeamRemoteToDataMapper {

    override fun map(item: TeamRemote): TeamData = with(item) {
        TeamData(
            id,
            fullName,
            division,
            abbreviation,
            conference,
            city,
            false
        )
    }
}