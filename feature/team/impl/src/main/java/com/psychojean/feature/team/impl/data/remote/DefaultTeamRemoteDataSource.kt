package com.psychojean.feature.team.impl.data.remote

import com.psychojean.feature.team.api.data.model.TeamData
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.data.remote.TeamRemoteToDataMapper
import com.psychojean.feature.team.impl.data.remote.service.TeamService

internal class DefaultTeamRemoteDataSource(
    private val teamService: TeamService,
    private val teamRemoteToDataMapper: TeamRemoteToDataMapper
): TeamRemoteDataSource {

    override suspend fun teams(): List<TeamData> =
        teamService.teams().teams?.map(teamRemoteToDataMapper::map)
            ?: emptyList()

    override suspend fun team(id: Int): TeamData =
        teamRemoteToDataMapper.map(
            teamService.team(id)
        )
}