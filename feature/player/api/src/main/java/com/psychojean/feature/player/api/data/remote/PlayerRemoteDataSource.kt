package com.psychojean.feature.player.api.data.remote

import com.psychojean.feature.player.api.data.model.PlayerData

interface PlayerRemoteDataSource {

    suspend fun player(id: Int): PlayerData
}