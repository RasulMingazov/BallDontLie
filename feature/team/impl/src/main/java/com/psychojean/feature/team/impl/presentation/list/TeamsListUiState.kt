package com.psychojean.feature.team.impl.presentation.list

import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.presentation.model.TeamModel

@androidx.compose.runtime.Immutable
internal data class TeamsListUiState(
    val isLoading: Boolean = true,
    val isRetry: Boolean = false,
    val error: ErrorType? = null,
    var teams: List<TeamModel>? = null,
    val isTeamsEmpty: Boolean = false,
    val isRefreshAvailable: Boolean = false
) {

    fun toRetry(errorType: ErrorType): TeamsListUiState = copy(
        isLoading = false,
        isRefreshAvailable = false,
        isRetry = true,
        error = errorType,
        teams = null
    )

    fun toTeams(teams: List<TeamModel>): TeamsListUiState = copy(
        teams = teams,
        isLoading = false,
        isRefreshAvailable = true,
        isRetry = false,
        isTeamsEmpty = false,
        error = null
    )

    fun toError(errorType: ErrorType) = copy(
        isLoading = false,
        isRetry = false,
        isRefreshAvailable = false,
        error = errorType,
        isTeamsEmpty = false,
        teams = null
    )

    fun toEmpty() = copy(
        isLoading = false,
        isRetry = false,
        isRefreshAvailable = true,
        error = null,
        isTeamsEmpty = true,
        teams = null
    )
}
