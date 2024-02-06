package com.psychojean.feature.player.impl.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.exceptionOrThrow
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.core.impl.presentation.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.core.impl.presentation.save.SaveStateKey
import com.psychojean.feature.player.impl.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.impl.presentation.detail.model.mapper.PlayerEntityToModelMapper
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
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper,
    private val playerEntityToModelMapper: PlayerEntityToModelMapper,
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
        _state.update { it.toRetry(errorType) }
        fetch()
    }

    private suspend fun fetch() = _state.update { uiState ->
        val playerResult = playerDetailInteractor.player(id)
        if (playerResult.isSuccess)
            uiState.toPlayer(playerEntityToModelMapper.map(playerResult.getOrThrow()))
        else
            uiState.toError(errorTypeMapper.map(playerResult.exceptionOrThrow()))
    }
}
