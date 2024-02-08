package com.psychojean.feature.player.impl.domain.detail

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.player.api.domain.PlayerRepository
import javax.inject.Inject

interface PlayerDetailInteractor {

    suspend fun player(id: Int): PlayerResult
}

internal class DefaultPlayerDetailInteractor @Inject constructor(
    private val playerRepository: PlayerRepository,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : PlayerDetailInteractor {

    override suspend fun player(id: Int): PlayerResult =
        try {
            PlayerResult.Success(playerRepository.player(id))
        } catch (exception: Exception) {
            PlayerResult.Error(errorTypeMapper.map(exception))
        }
}