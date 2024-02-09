package com.psychojean.feature.player.impl.presentation.list

import com.psychojean.core.api.error.ErrorType

internal data class PlayerListUiState(
    val isLoading: Boolean = true,
    val error: ErrorType? = null,
    val isRefreshAvailable: Boolean = false
) {

    fun copyToError(errorType: ErrorType) = copy(
        isLoading = false,
        error = errorType,
        isRefreshAvailable = false
    )

    fun copyToLoading() = copy(
        isLoading = true,
        error = null,
        isRefreshAvailable = false
    )

    fun copyToLoadingFinished() = copy(
        isLoading = false,
        error = null,
        isRefreshAvailable = true
    )
}
