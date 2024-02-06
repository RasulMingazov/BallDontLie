package com.psychojean.feature.player.api.domain.detail

interface PlayerDetailInteractor {

    suspend fun player(id: Int): PlayerResult

}
