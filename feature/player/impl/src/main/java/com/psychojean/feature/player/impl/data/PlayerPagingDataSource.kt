package com.psychojean.feature.player.impl.data

import com.psychojean.core.impl.data.paging.BasePagingDataSource
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PlayerPagingDataSource(
    private val playerRemoteDataSource: PlayerRemoteDataSource,
    private val playerDataToEntityMapper: PlayerDataToEntityMapper
) : BasePagingDataSource<PlayerEntity>() {

    override suspend fun pagingApiRequest(
        offset: Int,
        loadSize: Int
    ): Result<List<PlayerEntity>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                playerRemoteDataSource.players(offset, loadSize)
                    .map(playerDataToEntityMapper::map)
            }
        }
    }
}