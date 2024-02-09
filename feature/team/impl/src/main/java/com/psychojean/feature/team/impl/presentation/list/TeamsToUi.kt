package com.psychojean.feature.team.impl.presentation.list

import com.psychojean.core.api.ResultToUi
import com.psychojean.feature.team.impl.domain.list.TeamsResult
import com.psychojean.feature.team.impl.presentation.model.mapper.StarredTeamsMapper
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import javax.inject.Inject

internal interface TeamsToUi : ResultToUi<TeamsListUiState, TeamsResult>

internal class DefaultTeamsToUi @Inject constructor(
    private val teamEntityToModelMapper: TeamEntityToModelMapper,
    private val starredTeamsMapper: StarredTeamsMapper
) : TeamsToUi {

    override fun TeamsListUiState.copyFromResult(result: TeamsResult): TeamsListUiState =
        when (result) {
            is TeamsResult.Error -> copyToError(result.errorType)
            is TeamsResult.Empty -> copyToEmpty()
            is TeamsResult.Success -> copyToTeams(
                result.teams.map(teamEntityToModelMapper::map),
                starredTeamsMapper.map(result.starredTeamsCount)
            )
        }
}
