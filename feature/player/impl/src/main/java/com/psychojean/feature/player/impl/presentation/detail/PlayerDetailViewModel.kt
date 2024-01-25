package com.psychojean.feature.player.impl.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.Dispatcher
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.core.impl.presentation.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.core.impl.presentation.save.SaveStateKey
import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class PlayerDetailViewModel @Inject constructor(
    private val playerDetailInteractor: PlayerDetailInteractor,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper,
    private val dispatcher: Dispatcher,
    saveStateKey: SaveStateKey<Int>
) : ViewModel() {

    private val id = saveStateKey.value(PlayerDetailNavigationRoute.idArgumentKey)

    private val _state = MutableStateFlow<PlayerDetailState>(PlayerDetailState.Loading)
    val state = _state.asStateFlow()

    private val _event = Channel<PlayerDetailEvent>()
    val event = _event.receiveAsFlow()

    init {
        dispatcher.launchBackground(viewModelScope) {
            fetch().collect()
        }
    }

    fun reload(errorType: ErrorType) {
        dispatcher.launchBackground(viewModelScope) {
            fetch().onStart { _state.value = PlayerDetailState.Reload(errorType) }.collect()
        }
    }

    fun dismiss() {
        dispatcher.launchUI(viewModelScope) {
            _event.send(PlayerDetailEvent.Dismiss)
        }
    }

    private fun fetch() = playerDetailInteractor.player(id)
        .onEach { _state.value = PlayerDetailState.Success(it) }
        .catch { _state.value = PlayerDetailState.Error(errorTypeMapper.map(it)) }
}
