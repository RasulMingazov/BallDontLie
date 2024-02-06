package com.psychojean.feature.player.impl.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.error.ErrorType
import com.psychojean.core.impl.presentation.save.SaveStateKey
import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.api.domain.detail.PlayerResult
import com.psychojean.feature.player.impl.presentation.detail.state.PlayerDetailEvent
import com.psychojean.feature.player.impl.presentation.detail.state.PlayerDetailUiState
import com.psychojean.feature.player.impl.presentation.model.mapper.PlayerEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlayerDetailViewModel @Inject constructor(
    private val playerDetailInteractor: PlayerDetailInteractor,
    private val playerDetailEntityToModelMapper: PlayerEntityToModelMapper,
    saveStateKey: SaveStateKey<Int>
) : ViewModel() {

    private val id = saveStateKey.value(PlayerDetailNavigationRoute.idArgumentKey)

    private val _state = MutableStateFlow(PlayerDetailUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<PlayerDetailEvent>()
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch { fetch() }
    }

    fun dismiss() = viewModelScope.launch { _event.send(PlayerDetailEvent.Dismiss) }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.toRetry(errorType) }
        fetch()
    }

    private suspend fun fetch() = _state.update { uiState ->
        when (val playerResult = playerDetailInteractor.player(id)) {
            is PlayerResult.Error -> uiState.toError(playerResult.errorType)
            is PlayerResult.Success -> uiState.toPlayer(
                playerDetailEntityToModelMapper.map(playerResult.player)
            )
        }
    }
}
