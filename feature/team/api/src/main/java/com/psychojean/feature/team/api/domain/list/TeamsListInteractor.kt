package com.psychojean.feature.team.api.domain.list

import com.psychojean.feature.team.api.domain.model.TeamEntity
import kotlinx.coroutines.flow.Flow

interface TeamsListInteractor {

    fun players(): Flow<List<TeamEntity>>

}