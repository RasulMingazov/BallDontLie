package com.psychojean.feature.player.impl.presentation.list

import com.psychojean.core.api.error.ErrorType

internal data class PlayerListUiState(
    val isLoading: Boolean = true,
    val error: ErrorType? = null
) {

    fun toError(errorType: ErrorType) = copy(
        isLoading = false,
        error = errorType
    )

    fun toLoading() = copy(
        isLoading = true,
        error = null
    )

    fun toLoadingFinished() = copy(
        isLoading = false,
        error = null
    )
}
