package com.psychojean.feature.player.api.domain

import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    fun player(id: Int): Flow<PlayerEntity>
}