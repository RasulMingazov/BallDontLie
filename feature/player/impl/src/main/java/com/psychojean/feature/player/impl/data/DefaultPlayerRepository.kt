package com.psychojean.feature.player.impl.data

import com.psychojean.feature.player.api.data.model.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class DefaultPlayerRepository(
    private val playerRemoteDataSource: PlayerRemoteDataSource,
    private val playerDataToEntityMapper: PlayerDataToEntityMapper
): PlayerRepository {

    override fun player(id: Int): Flow<PlayerEntity> = flow {
        emit(playerRemoteDataSource.player(id))
    }.map(playerDataToEntityMapper::map)
}