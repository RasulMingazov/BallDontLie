package com.psychojean.feature.team.impl.presentation.list

import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.presentation.model.TeamModel

@androidx.compose.runtime.Immutable
internal data class TeamsListUiState(
    val isLoading: Boolean = true,
    val isRetry: Boolean = false,
    val error: ErrorType? = null,
    val teams: List<TeamModel> = emptyList(),
) {

    fun toRetry(errorType: ErrorType): TeamsListUiState = copy(
        isLoading = false,
        isRetry = true,
        error = errorType,
        teams = emptyList()
    )

    fun toTeams(teams: List<TeamModel>): TeamsListUiState = copy(
        teams = teams,
        isLoading = false,
        isRetry = false,
        error = null
    )

    fun toError(errorType: ErrorType) = copy(
        isLoading = false,
        isRetry = false,
        error = errorType,
        teams = emptyList()
    )
}
