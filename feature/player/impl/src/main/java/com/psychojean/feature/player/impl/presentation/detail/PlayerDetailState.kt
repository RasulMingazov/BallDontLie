
package com.psychojean.feature.player.impl.presentation.detail

import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel

internal sealed class PlayerDetailState {
    data object Loading : PlayerDetailState()
    data class Refresh(val errorType: ErrorType) : PlayerDetailState()
    data class Success(val player: PlayerModel) : PlayerDetailState()
    data class Error(val errorType: ErrorType) : PlayerDetailState()
}
