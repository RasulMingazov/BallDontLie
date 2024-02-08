package com.psychojean.feature.team.impl.data.local

import com.psychojean.core.impl.data.database.team.TeamDao
import com.psychojean.feature.team.api.data.local.TeamLocalDataSource
import com.psychojean.feature.team.api.data.model.TeamData
import com.psychojean.feature.team.impl.data.local.mapper.TeamDataToLocalMapper
import com.psychojean.feature.team.impl.data.local.mapper.TeamLocalToDataMapper
import javax.inject.Inject

internal class DefaultTeamLocalDataSource @Inject constructor(
    private val teamDao: TeamDao,
    private val teamLocalToDataMapper: TeamLocalToDataMapper,
    private val teamDataToLocalMapper: TeamDataToLocalMapper
) : TeamLocalDataSource {

    override suspend fun clear() = teamDao.clear()

    override suspend fun teams(): List<TeamData> =
        teamDao.teams().map(teamLocalToDataMapper::map)

    override suspend fun starredTeams(): List<TeamData> =
        teamDao.starredTeams().map(teamLocalToDataMapper::map)

    override suspend fun insert(team: TeamData) = teamDao.insert(teamDataToLocalMapper.map(team))

    override suspend fun insert(teams: List<TeamData>) =
        teamDao.insert(teams.map(teamDataToLocalMapper::map))

    override suspend fun star(id: Int, isStarred: Boolean) = teamDao.star(id, isStarred)

    override suspend fun starredTeamsCount(): Int = teamDao.starredTeamsCount()

}
