package com.psychojean.feature.player.impl.domain.list

import androidx.paging.PagingData
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PlayersListInteractor {

    fun players(): Flow<PagingData<PlayerEntity>>

}

internal class DefaultPlayersListInteractor @Inject constructor(
    private val playerRepository: PlayerRepository
) : PlayersListInteractor {

    override fun players(): Flow<PagingData<PlayerEntity>> =
        playerRepository.players()
}