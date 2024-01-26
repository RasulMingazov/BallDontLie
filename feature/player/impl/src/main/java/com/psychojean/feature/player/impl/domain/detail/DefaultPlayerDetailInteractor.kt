package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultPlayerDetailInteractor @Inject constructor(
    private val playerRepository: PlayerRepository
) : PlayerDetailInteractor {

    override fun player(id: Int): Flow<PlayerEntity> =
        playerRepository.player(id)
}