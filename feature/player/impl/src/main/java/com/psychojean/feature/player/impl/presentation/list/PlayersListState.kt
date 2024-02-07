package com.psychojean.feature.player.impl.presentation.list

import com.psychojean.core.api.error.ErrorType

internal data class PlayerListUiState(
    val isLoading: Boolean = true,
    val error: ErrorType? = null,
    val isRefreshAvailable: Boolean = false
) {

    fun toError(errorType: ErrorType) = copy(
        isLoading = false,
        error = errorType,
        isRefreshAvailable = false
    )

    fun toLoading() = copy(
        isLoading = true,
        error = null,
        isRefreshAvailable = false
    )

    fun toLoadingFinished() = copy(
        isLoading = false,
        error = null,
        isRefreshAvailable = true
    )
}
