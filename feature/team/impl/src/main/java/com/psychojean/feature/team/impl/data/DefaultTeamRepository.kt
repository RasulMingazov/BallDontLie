package com.psychojean.feature.team.impl.data

import com.psychojean.core.impl.data.BaseRepository
import com.psychojean.feature.team.api.data.local.TeamLocalDataSource
import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.api.domain.model.TeamEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultTeamRepository @Inject constructor(
    private val teamRemoteDataSource: TeamRemoteDataSource,
    private val teamLocalDataSource: TeamLocalDataSource,
    private val teamDataToEntityMapper: TeamDataToEntityMapper
) : TeamRepository, BaseRepository() {

    private var isTeamsFirstCall: Boolean = true
    private var teamsLock = Mutex()

    override suspend fun teams(): List<TeamEntity> = withContext(Dispatchers.IO) {
        teamsLock.withLock {
            if (isTeamsFirstCall || teamLocalDataSource.teamsCount() == 0) {
                isTeamsFirstCall = false
                teamLocalDataSource.clear()
                teamLocalDataSource.insert(teamRemoteDataSource.teams())
            }
        }
        teamLocalDataSource.teams().map(teamDataToEntityMapper::map)
    }

    override suspend fun starredTeams(): List<TeamEntity> = withContext(Dispatchers.IO) {
        teamLocalDataSource.starredTeams().map(teamDataToEntityMapper::map)
    }

    override suspend fun star(teamId: Int, isStarred: Boolean) = withContext(Dispatchers.IO) {
        teamLocalDataSource.star(teamId, isStarred)
    }

    override suspend fun starredTeamsCount(): Int =
        withContext(Dispatchers.IO) { teamLocalDataSource.starredTeamsCount() }
}