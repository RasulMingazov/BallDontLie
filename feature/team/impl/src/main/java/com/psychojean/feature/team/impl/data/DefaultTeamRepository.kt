package com.psychojean.feature.team.impl.data

import com.psychojean.core.impl.data.BaseRepository
import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.api.domain.model.TeamEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class DefaultTeamRepository(
    private val teamRemoteDataSource: TeamRemoteDataSource,
    private val teamDataToEntityMapper: TeamDataToEntityMapper
) : TeamRepository, BaseRepository() {

    override fun team(id: Int): Flow<TeamEntity> = flow {
        emit(teamRemoteDataSource.team(id))
    }.map(teamDataToEntityMapper::map)

    override fun teams(): Flow<List<TeamEntity>> = flow {
        emit(teamRemoteDataSource.teams())
    }.map { it.map(teamDataToEntityMapper::map) }
}