package com.psychojean.feature.team.impl.data.remote.service

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.impl.data.BaseApiDecorator
import com.psychojean.feature.team.api.data.remote.model.TeamRemote
import com.psychojean.feature.team.api.data.remote.model.TeamsRemote

internal class TeamServiceDecorator(
    private val service: TeamService,
    serverExceptionMapper: ServerExceptionMapper
) : BaseApiDecorator(serverExceptionMapper), TeamService by service {

    override suspend fun teams(): TeamsRemote = baseRequest {
        service.teams()
    }

    override suspend fun team(id: Int): TeamRemote = baseRequest {
        service.team(id)
    }
}