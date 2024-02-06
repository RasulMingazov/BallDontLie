package com.psychojean.feature.player.impl.presentation.detail

import androidx.compose.runtime.Immutable
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel

@Immutable
internal data class PlayerDetailUiState(
    val isLoading: Boolean = true,
    val isRetry: Boolean = false,
    val error: ErrorType? = null,
    val player: PlayerModel? = null,
) {

    fun toRetry(errorType: ErrorType): PlayerDetailUiState = copy(
        isLoading = false,
        isRetry = true,
        error = errorType,
        player = null
    )

    fun toPlayer(player: PlayerModel): PlayerDetailUiState = copy(
        player = player,
        isLoading = false,
        isRetry = false,
        error = null
    )

    fun toError(errorType: ErrorType) = copy(
        isLoading = false,
        isRetry = false,
        error = errorType,
        player = null
    )
}