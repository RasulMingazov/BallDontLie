package com.psychojean.feature.player.api.domain.detail

import com.psychojean.feature.player.api.domain.detail.model.PlayerDetailEntity
import kotlinx.coroutines.flow.Flow

interface PlayerDetailInteractor {

    fun player(id: Int): Flow<PlayerDetailEntity>

}