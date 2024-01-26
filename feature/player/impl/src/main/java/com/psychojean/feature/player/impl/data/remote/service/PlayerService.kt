package com.psychojean.feature.player.impl.data.remote.service

import com.psychojean.feature.player.api.data.remote.model.PlayerRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerService {

    @GET("players/{id}")
    suspend fun player(
        @Path("id") id: Int
    ): PlayerRemote
}