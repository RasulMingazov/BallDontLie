package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.player.PlayerData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.impl.data.remote.service.PlayerService

internal class DefaultPlayerRemoteDataSource(
    private val playerService: PlayerService,
    private val playerRemoteToDataMapper: PlayerRemoteToDataMapper
) : PlayerRemoteDataSource {

    override suspend fun players(page: Int, perPage: Int): List<PlayerData> =
        playerService.players(page, perPage).players?.map(playerRemoteToDataMapper::map)
            ?: emptyList()

    override suspend fun player(id: Int): PlayerData =
        playerRemoteToDataMapper.map(
            playerService.player(id)
        )
}