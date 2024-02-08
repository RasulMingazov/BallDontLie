package com.psychojean.feature.team.impl.presentation.starred

import androidx.compose.runtime.Immutable
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.presentation.model.TeamModel

@Immutable
internal data class StarredTeamsListUiState(
    val isLoading: Boolean = true,
    val isRetry: Boolean = false,
    val error: ErrorType? = null,
    val teams: List<TeamModel>? = null,
    val isTeamsEmpty: Boolean = false,
) {

    fun copyToRetry(errorType: ErrorType): StarredTeamsListUiState = copy(
        isLoading = false,
        isRetry = true,
        error = errorType,
        teams = null
    )

    fun copyToTeams(teams: List<TeamModel>): StarredTeamsListUiState = copy(
        teams = teams,
        isLoading = false,
        isRetry = false,
        isTeamsEmpty = false,
        error = null
    )

    fun copyToError(errorType: ErrorType) = copy(
        isLoading = false,
        isRetry = false,
        error = errorType,
        isTeamsEmpty = false,
        teams = null
    )

    fun copyToEmpty() = copy(
        isLoading = false,
        isRetry = false,
        error = null,
        isTeamsEmpty = true,
        teams = null
    )
}