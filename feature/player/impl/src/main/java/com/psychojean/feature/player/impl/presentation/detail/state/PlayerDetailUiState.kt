package com.psychojean.feature.player.impl.presentation.detail.state

import androidx.compose.runtime.Immutable
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.player.impl.presentation.model.PlayerModel

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
        error = errorType
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
        error = errorType
    )
}