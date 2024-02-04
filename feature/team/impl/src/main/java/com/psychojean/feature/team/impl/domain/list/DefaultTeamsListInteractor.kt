package com.psychojean.feature.team.impl.domain.list

import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.api.domain.model.TeamEntity
import com.psychojean.feature.team.api.domain.list.TeamsListInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultTeamsListInteractor @Inject constructor(
    private val teamRepository: TeamRepository
) : TeamsListInteractor {

    override fun players(): Flow<List<TeamEntity>> =
        teamRepository.teams()
}