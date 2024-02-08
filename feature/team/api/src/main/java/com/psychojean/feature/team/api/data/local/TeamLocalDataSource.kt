package com.psychojean.feature.team.api.data.local

import com.psychojean.feature.team.api.data.model.TeamData

interface TeamLocalDataSource {

    suspend fun insert(team: TeamData)

    suspend fun insert(teams: List<TeamData>)

    suspend fun star(id: Int, isStarred: Boolean)

    suspend fun clear()

    suspend fun teams(): List<TeamData>

    suspend fun starredTeams(): List<TeamData>

    suspend fun starredTeamsCount(): Int
}