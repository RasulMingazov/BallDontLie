package com.psychojean.feature.player.impl.data.remote.service

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.impl.data.BaseApiDecorator
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote

class PlayerServiceDecorator(
    private val service: PlayerService,
    serverExceptionMapper: ServerExceptionMapper
) : BaseApiDecorator(serverExceptionMapper), PlayerService by service {

    override suspend fun player(id: Int): PlayerRemote = baseRequest {
        service.player(id)
    }
}