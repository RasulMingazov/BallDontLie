package com.psychojean.feature.team.impl.data.remote.service

import com.psychojean.feature.team.api.data.remote.model.TeamRemote
import com.psychojean.feature.team.api.data.remote.model.TeamsRemote
import retrofit2.http.GET
import retrofit2.http.Path

internal interface TeamService {

    @GET("teams/")
    suspend fun teams(): TeamsRemote


    @GET("players/{id}")
    suspend fun team(
        @Path("id") id: Int
    ): TeamRemote
}