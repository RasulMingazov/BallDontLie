package com.psychojean.feature.player.impl.data

import androidx.paging.PagingData
import com.psychojean.core.impl.data.BaseRepository
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultPlayerRepository @Inject constructor(
    private val playerRemoteDataSource: PlayerRemoteDataSource,
    private val playerDataToEntityMapper: PlayerDataToEntityMapper
) : PlayerRepository, BaseRepository() {

    override suspend fun player(id: Int) = withContext(Dispatchers.IO) {
        playerDataToEntityMapper.map(playerRemoteDataSource.player(id))
    }

    override fun players(): Flow<PagingData<PlayerEntity>> =
        doPagingRequest(PlayerPagingDataSource(playerRemoteDataSource, playerDataToEntityMapper))
}