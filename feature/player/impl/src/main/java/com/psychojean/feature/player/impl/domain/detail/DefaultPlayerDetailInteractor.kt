package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.api.domain.detail.model.PlayerDetailEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultPlayerDetailInteractor @Inject constructor() : PlayerDetailInteractor {

    override fun player(id: Int): Flow<PlayerDetailEntity> = flow {
        delay(1_000)
        emit(PlayerDetailEntity(0, "Michael", "Jordan"))
    }
}