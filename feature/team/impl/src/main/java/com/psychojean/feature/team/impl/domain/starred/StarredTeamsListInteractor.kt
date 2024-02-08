package com.psychojean.feature.team.impl.domain.starred

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.team.api.domain.TeamRepository
import javax.inject.Inject

internal interface StarredTeamsListInteractor {

    suspend fun starredTeams(): StarredTeamsResult

    suspend fun removeFromStarred(teamId: Int): Result<Unit>

}

internal class DefaultStarredTeamsListInteractor @Inject constructor(
    private val teamRepository: TeamRepository,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : StarredTeamsListInteractor {

    override suspend fun starredTeams(): StarredTeamsResult = try {
        val starredTeams = teamRepository.starredTeams()
        if (starredTeams.isNotEmpty())
            StarredTeamsResult.Success(starredTeams)
        else StarredTeamsResult.Empty
    } catch (exception: Exception) {
        StarredTeamsResult.Error(errorTypeMapper.map(exception))
    }

    override suspend fun removeFromStarred(teamId: Int): Result<Unit> =
        runCatching { teamRepository.star(teamId, false) }
}
