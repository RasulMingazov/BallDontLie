package com.psychojean.feature.team.impl.domain.list

import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.api.domain.model.TeamEntity
import javax.inject.Inject

internal class DefaultTeamsListInteractor @Inject constructor(
    private val teamRepository: TeamRepository
) : TeamsListInteractor {

    override suspend fun teams(): Result<List<TeamEntity>> =
        runCatching { teamRepository.teams() }
}