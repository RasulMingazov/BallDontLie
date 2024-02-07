package com.psychojean.feature.team.impl.data

import com.psychojean.core.impl.data.BaseRepository
import com.psychojean.feature.team.api.data.local.TeamLocalDataSource
import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.api.domain.model.TeamEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DefaultTeamRepository(
    private val teamRemoteDataSource: TeamRemoteDataSource,
    private val teamLocalDataSource: TeamLocalDataSource,
    private val teamDataToEntityMapper: TeamDataToEntityMapper
) : TeamRepository, BaseRepository() {

    override suspend fun teams(): List<TeamEntity> = withContext(Dispatchers.IO) {
        val remoteTeams = teamRemoteDataSource.teams()
        teamLocalDataSource.insert(remoteTeams)
        teamLocalDataSource.teams().map(teamDataToEntityMapper::map)
    }

    override suspend fun starredTeams(): List<TeamEntity> = withContext(Dispatchers.IO) {
        teamLocalDataSource.starredTeams().map(teamDataToEntityMapper::map)
    }

    override suspend fun star(teamId: Int, isStarred: Boolean) = withContext(Dispatchers.IO) {
        teamLocalDataSource.star(teamId, isStarred)
    }
}