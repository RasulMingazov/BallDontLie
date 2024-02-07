package com.psychojean.feature.player.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.core.impl.presentation.paging.PagingStateListener
import com.psychojean.feature.player.impl.domain.list.PlayersListInteractor
import com.psychojean.feature.player.impl.presentation.model.mapper.PlayerEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlayersListViewModel @Inject constructor(
    playersListInteractor: PlayersListInteractor,
    private val playerEntityToModelMapper: PlayerEntityToModelMapper,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : ViewModel(), PagingStateListener {

    val players = playersListInteractor.players()
        .map { it.map(playerEntityToModelMapper::map) }
        .cachedIn(viewModelScope)

    private val _state = MutableStateFlow(PlayerListUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<PlayersListEvent>()
    val event = _event.receiveAsFlow()

    override fun loadingError(throwable: Throwable) {
        _state.update { uiState -> uiState.toError(errorTypeMapper.map(throwable)) }
    }

    override fun loadingStarted() {
        _state.update { uiState -> uiState.toLoading() }
    }

    override fun loadingFinished() {
        _state.update { uiState -> uiState.toLoadingFinished() }
    }

    fun retry() = viewModelScope.launch { _event.send(PlayersListEvent.Retry) }

    fun refresh() = viewModelScope.launch {
        //todo add mediator
        _event.send(PlayersListEvent.Retry)
        _event.send(PlayersListEvent.StartRefresh)
        delay(1500)
        _event.send(PlayersListEvent.EndRefresh)
    }
}
