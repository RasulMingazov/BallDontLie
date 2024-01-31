package com.psychojean.feature.player.impl.data

import androidx.paging.PagingData
import com.psychojean.core.impl.data.BaseRepository
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class DefaultPlayerRepository(
    private val playerRemoteDataSource: PlayerRemoteDataSource,
    private val playerDataToEntityMapper: PlayerDataToEntityMapper
) : PlayerRepository, BaseRepository() {

    override fun player(id: Int): Flow<PlayerEntity> = flow {
        emit(playerRemoteDataSource.player(id))
    }.map(playerDataToEntityMapper::map)

    override fun players(): Flow<PagingData<PlayerEntity>> =
        doPagingRequest(PlayerPagingDataSource(playerRemoteDataSource, playerDataToEntityMapper))
}