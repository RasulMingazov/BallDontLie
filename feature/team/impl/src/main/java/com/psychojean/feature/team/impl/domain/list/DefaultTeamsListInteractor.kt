package com.psychojean.feature.team.impl.domain.list

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.team.api.domain.TeamRepository
import javax.inject.Inject

internal class DefaultTeamsListInteractor @Inject constructor(
    private val teamRepository: TeamRepository,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : TeamsListInteractor {

    override suspend fun star(teamId: Int, isStarred: Boolean) {
        runCatching { teamRepository.star(teamId, isStarred) }
    }

    override suspend fun teams() = try {
        TeamsResult.Success(teamRepository.teams())
    } catch (exception: Exception) {
        TeamsResult.Error(errorTypeMapper.map(exception))
    }
}
