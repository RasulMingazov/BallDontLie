package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.api.domain.detail.PlayerResult
import javax.inject.Inject

internal class DefaultPlayerDetailInteractor @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val errorTypeMapper: ErrorTypeMapper
) : PlayerDetailInteractor {

    override suspend fun player(id: Int): PlayerResult =
        try {
            PlayerResult.Success(playerRepository.player(id))
        } catch (exception: Exception) {
            PlayerResult.Error(errorTypeMapper.map(exception))
        }
}