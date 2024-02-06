package com.psychojean.feature.team.impl.data

import com.psychojean.core.impl.data.BaseRepository
import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.api.domain.model.TeamEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class DefaultTeamRepository(
    private val teamRemoteDataSource: TeamRemoteDataSource,
    private val teamDataToEntityMapper: TeamDataToEntityMapper
) : TeamRepository, BaseRepository() {

    override suspend fun teams(): List<TeamEntity> = withContext(Dispatchers.IO) {
        teamRemoteDataSource.teams().map(teamDataToEntityMapper::map)
    }
}