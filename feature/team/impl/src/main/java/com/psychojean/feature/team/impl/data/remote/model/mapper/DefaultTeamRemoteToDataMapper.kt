package com.psychojean.feature.team.impl.data.remote.model.mapper

import com.psychojean.feature.team.api.data.model.TeamData
import com.psychojean.feature.team.api.data.remote.TeamRemoteToDataMapper
import com.psychojean.feature.team.api.data.remote.model.TeamRemote
import javax.inject.Inject

internal class DefaultTeamRemoteToDataMapper @Inject constructor() : TeamRemoteToDataMapper {

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