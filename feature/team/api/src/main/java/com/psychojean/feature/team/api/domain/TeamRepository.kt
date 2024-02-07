package com.psychojean.feature.team.api.domain

import com.psychojean.feature.team.api.domain.model.TeamEntity

interface TeamRepository {

    suspend fun teams(): List<TeamEntity>

    suspend fun starredTeams(): List<TeamEntity>

    suspend fun star(teamId: Int, isStarred: Boolean)

}