package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity

sealed class PlayerResult {

    data class Success(val player: PlayerEntity): PlayerResult()

    data class Error(val errorType: ErrorType): PlayerResult()
}