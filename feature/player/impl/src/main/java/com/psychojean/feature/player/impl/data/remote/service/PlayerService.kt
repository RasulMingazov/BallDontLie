package com.psychojean.feature.player.impl.data.remote.service

import com.psychojean.feature.player.api.data.remote.model.PlayerRemote
import com.psychojean.feature.player.api.data.remote.model.PlayersRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerService {

    @GET("players/")
    suspend fun players(
        @Query("page") page: Int = 0,
        @Query("per_page") perPage: Int = 25,
    ): PlayersRemote


    @GET("players/{id}")
    suspend fun player(
        @Path("id") id: Int
    ): PlayerRemote
}