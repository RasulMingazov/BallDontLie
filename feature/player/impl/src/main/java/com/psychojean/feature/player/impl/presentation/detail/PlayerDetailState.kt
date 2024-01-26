
package com.psychojean.feature.player.impl.presentation.detail

import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity

internal sealed class PlayerDetailState {
    data object Loading : PlayerDetailState()
    data class Reload(val errorType: ErrorType) : PlayerDetailState()
    data class Success(val player: PlayerEntity) : PlayerDetailState()
    data class Error(val errorType: ErrorType) : PlayerDetailState()
}
