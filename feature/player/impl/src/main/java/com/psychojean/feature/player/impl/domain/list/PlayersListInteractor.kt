package com.psychojean.feature.player.impl.domain.list

import androidx.paging.PagingData
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayersListInteractor {

    fun players(): Flow<PagingData<PlayerEntity>>

}