package com.psychojean.feature.team.impl.presentation.starred

import com.psychojean.core.api.ResultToUi
import com.psychojean.feature.team.impl.domain.starred.StarredTeamsResult
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import javax.inject.Inject

internal interface StarredTeamsToUi : ResultToUi<StarredTeamsListUiState, StarredTeamsResult>

internal class DefaultStarredTeamsToUi @Inject constructor(
    private val teamEntityToModelMapper: TeamEntityToModelMapper
) : StarredTeamsToUi {

    override fun StarredTeamsListUiState.copyFromResult(result: StarredTeamsResult): StarredTeamsListUiState =
        when (result) {
            is StarredTeamsResult.Error -> copyToError(result.errorType)
            is StarredTeamsResult.Empty -> copyToEmpty()
            is StarredTeamsResult.Success -> copyToTeams(
                result.teams.map(teamEntityToModelMapper::map)
            )
        }
}
