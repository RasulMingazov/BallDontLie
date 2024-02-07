package com.psychojean.feature.team.impl.domain.starred

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.impl.domain.list.TeamsResult
import javax.inject.Inject

class DefaultStarredTeamsListInteractor @Inject constructor(
    private val teamRepository: TeamRepository,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : StarredTeamsListInteractor {

    override suspend fun teams(): TeamsResult = try {
        val starredTeams = teamRepository.starredTeams()
        if (starredTeams.isNotEmpty())
            TeamsResult.Success(starredTeams)
        else TeamsResult.Empty
    } catch (exception: Exception) {
        TeamsResult.Error(errorTypeMapper.map(exception))
    }

    override suspend fun removeFromStarred(teamId: Int) {
        runCatching { teamRepository.star(teamId, false) }
    }
}