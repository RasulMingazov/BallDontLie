package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.PlayerData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.impl.data.remote.service.PlayerService

internal class DefaultPlayerRemoteDataSource(
    private val playerService: PlayerService,
    private val playerRemoteToDataMapper: PlayerRemoteToDataMapper
): PlayerRemoteDataSource {

    override suspend fun player(id: Int): PlayerData =
        playerRemoteToDataMapper.map(
            playerService.player(id)
        )
}