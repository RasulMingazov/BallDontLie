package com.psychojean.feature.team.impl.presentation.list

import androidx.compose.runtime.Immutable
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.presentation.model.TeamModel

@Immutable
internal data class TeamsListUiState(
    val isLoading: Boolean = true,
    val isRetry: Boolean = false,
    val error: ErrorType? = null,
    val teams: List<TeamModel>? = null,
    val isTeamsEmpty: Boolean = false,
    val starredTeamsCount: String? = null,
    val isRefreshAvailable: Boolean = false
) {

    fun copyToRetry(errorType: ErrorType): TeamsListUiState = copy(
        isLoading = false,
        isRefreshAvailable = false,
        isRetry = true,
        error = errorType,
        starredTeamsCount = null,
        teams = null
    )

    fun copyToTeams(teams: List<TeamModel>, starredTeamsCount: String): TeamsListUiState = copy(
        teams = teams,
        isLoading = false,
        isRefreshAvailable = true,
        isRetry = false,
        isTeamsEmpty = false,
        starredTeamsCount = starredTeamsCount,
        error = null
    )

    fun copyToError(errorType: ErrorType) = copy(
        isLoading = false,
        isRetry = false,
        isRefreshAvailable = false,
        error = errorType,
        isTeamsEmpty = false,
        starredTeamsCount = null,
        teams = null
    )

    fun copyToEmpty() = copy(
        isLoading = false,
        isRetry = false,
        isRefreshAvailable = true,
        error = null,
        isTeamsEmpty = true,
        starredTeamsCount = null,
        teams = null
    )
}
