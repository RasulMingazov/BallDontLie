package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity

internal interface PlayerDetailInteractor {

    suspend fun player(id: Int): Result<PlayerEntity>

}
