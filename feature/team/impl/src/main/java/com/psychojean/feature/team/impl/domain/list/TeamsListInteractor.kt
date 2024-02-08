package com.psychojean.feature.team.impl.domain.list

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.team.api.domain.TeamRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

internal interface TeamsListInteractor {

    suspend fun star(teamId: Int, isStarred: Boolean): Result<Unit>

    suspend fun teams(): TeamsResult

}

internal class DefaultTeamsListInteractor @Inject constructor(
    private val teamRepository: TeamRepository,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : TeamsListInteractor {

    override suspend fun star(teamId: Int, isStarred: Boolean) =
        runCatching { teamRepository.star(teamId, isStarred) }

    override suspend fun teams(): TeamsResult = supervisorScope {
        val teamsAsync = async { teamRepository.teams() }
        val starredTeamsCountAsync = async { teamRepository.starredTeamsCount() }
        try {
            val teams = teamsAsync.await()
            val starredTeamsCount = starredTeamsCountAsync.await()
            if (teams.isNotEmpty())
                TeamsResult.Success(teams, starredTeamsCount)
            else TeamsResult.Empty
        } catch (exception: Exception) {
            TeamsResult.Error(errorTypeMapper.map(exception))
        }
    }
}
