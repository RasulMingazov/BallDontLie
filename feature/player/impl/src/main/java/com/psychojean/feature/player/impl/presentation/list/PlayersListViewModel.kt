package com.psychojean.feature.player.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.psychojean.core.api.Dispatcher
import com.psychojean.core.impl.presentation.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.core.impl.presentation.paging.PagingStateListener
import com.psychojean.feature.player.api.domain.list.PlayersListInteractor
import com.psychojean.feature.player.impl.presentation.detail.model.mapper.PlayerEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class PlayersListViewModel @Inject constructor(
    playersListInteractor: PlayersListInteractor,
    private val playerEntityToModelMapper: PlayerEntityToModelMapper,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper,
    private val dispatcher: Dispatcher
) : ViewModel(), PagingStateListener {

    val players = playersListInteractor.players()
        .map { it.map(playerEntityToModelMapper::map) }
        .catch { loadingError(it) }
        .cachedIn(viewModelScope)

    private val _state = MutableStateFlow<PlayersListState>(PlayersListState.Loading)
    val state = _state.asStateFlow()

    private val _appendState =
        MutableStateFlow<PlayersListAppendState>(PlayersListAppendState.NotLoading)
    val appendState = _appendState.asStateFlow()

    private val _event = Channel<PlayersListEvent>()
    val event = _event.receiveAsFlow()

    override fun loadingError(throwable: Throwable) {
        _state.value = PlayersListState.Error(errorTypeMapper.map(throwable))
    }

    override fun loadingStarted() {
        _state.value = PlayersListState.Loading
    }

    override fun loadingFinished() {
        _state.value = PlayersListState.NotLoading
    }

    override fun appendLoadingFinished() {
        _appendState.value = PlayersListAppendState.NotLoading
    }

    override fun appendLoadingStarted() {
        _appendState.value = PlayersListAppendState.Loading
    }

    override fun appendLoadingError(throwable: Throwable) {
        _appendState.value = PlayersListAppendState.Error(errorTypeMapper.map(throwable))
    }

    fun retry() {
        dispatcher.launchUI(viewModelScope) {
            _event.send(PlayersListEvent.Retry)
        }
    }

    fun refresh() {
        dispatcher.launchUI(viewModelScope) {
            _event.send(PlayersListEvent.Refresh)
            delay(1500)
            _event.send(PlayersListEvent.EndRefresh)
        }
    }
}
