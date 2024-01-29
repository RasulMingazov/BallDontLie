package com.psychojean.feature.player.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.psychojean.feature.player.api.domain.list.PlayersListInteractor
import com.psychojean.feature.player.impl.presentation.detail.model.mapper.DefaultPlayerEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class PlayersListViewModel @Inject constructor(
    playersListInteractor: PlayersListInteractor,
    private val playerEntityToModelMapper: DefaultPlayerEntityToModelMapper
) : ViewModel() {

    val players = playersListInteractor.players()
        .map { it.map(playerEntityToModelMapper::map) }
        .catch {  }
        .cachedIn(viewModelScope)
}