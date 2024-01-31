package com.psychojean.feature.player.api.data.remote

import com.psychojean.feature.player.api.data.model.player.PlayerData

interface PlayerRemoteDataSource {

    suspend fun players(
       page: Int,
        perPage: Int,
    ): List<PlayerData>

    suspend fun player(id: Int): PlayerData
}