package com.psychojean.feature.team.api.domain

import com.psychojean.feature.team.api.domain.model.TeamEntity
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun team(id: Int): Flow<TeamEntity>

    fun teams(): Flow<List<TeamEntity>>
}