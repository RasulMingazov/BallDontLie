package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import javax.inject.Inject

internal class DefaultPlayerDetailInteractor @Inject constructor(
    private val playerRepository: PlayerRepository,
) : PlayerDetailInteractor {

    override suspend fun player(id: Int): Result<PlayerEntity> = runCatching {
        playerRepository.player(id)
    }
}
