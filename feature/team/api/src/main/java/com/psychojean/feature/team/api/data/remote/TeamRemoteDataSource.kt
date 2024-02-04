package com.psychojean.feature.team.api.data.remote

import com.psychojean.feature.team.api.data.model.TeamData

interface TeamRemoteDataSource {

    suspend fun teams(): List<TeamData>

    suspend fun team(id: Int): TeamData
}