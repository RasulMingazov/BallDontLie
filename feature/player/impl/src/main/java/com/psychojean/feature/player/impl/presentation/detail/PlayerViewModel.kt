package com.psychojean.feature.player.impl.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.error.ErrorType
import com.psychojean.core.impl.presentation.save.SaveStateKey
import com.psychojean.feature.player.impl.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.impl.domain.detail.PlayerResult
import com.psychojean.feature.player.impl.presentation.model.PlayerEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlayerViewModel @Inject constructor(
    private val playerDetailInteractor: PlayerDetailInteractor,
    private val playerDetailEntityToModelMapper: PlayerEntityToModelMapper,
    saveStateKey: SaveStateKey<Int>
) : ViewModel() {

    private val id = saveStateKey.value(PlayerNavigationRoute.idArgumentKey)

    private val _state = MutableStateFlow(PlayerUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch { fetchPlayer() }
    }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.copyToRetry(errorType) }
        fetchPlayer()
    }

    private suspend fun fetchPlayer() = _state.update { uiState ->
        when (val playerResult = playerDetailInteractor.player(id)) {
            is PlayerResult.Error -> uiState.copyToError(playerResult.errorType)
            is PlayerResult.Success -> uiState.copyToPlayer(
                playerDetailEntityToModelMapper.map(playerResult.player)
            )
        }
    }
}
