package com.psychojean.feature.team.impl.domain.list

import com.psychojean.feature.team.api.domain.model.TeamEntity

interface TeamsListInteractor {

    suspend fun teams(): Result<List<TeamEntity>>

}