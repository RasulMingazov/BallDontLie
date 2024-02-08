package com.psychojean.feature.player.impl.presentation.detail

import androidx.compose.runtime.Immutable
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.player.impl.presentation.model.PlayerModel

@Immutable
internal data class PlayerUiState(
    val isLoading: Boolean = true,
    val isRetry: Boolean = false,
    val error: ErrorType? = null,
    val player: PlayerModel? = null,
) {

    fun copyToRetry(errorType: ErrorType): PlayerUiState = copy(
        isLoading = false,
        isRetry = true,
        error = errorType
    )

    fun copyToPlayer(player: PlayerModel): PlayerUiState = copy(
        player = player,
        isLoading = false,
        isRetry = false,
        error = null
    )

    fun copyToError(errorType: ErrorType) = copy(
        isLoading = false,
        isRetry = false,
        error = errorType
    )
}