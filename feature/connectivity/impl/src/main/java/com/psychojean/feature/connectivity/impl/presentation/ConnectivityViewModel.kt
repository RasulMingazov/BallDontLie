package com.psychojean.feature.connectivity.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.feature.connectivity.api.ConnectivityService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectivityViewModel @Inject constructor(
    private val connectivityService: ConnectivityService
) : ViewModel() {

    private val _state = MutableStateFlow(ConnectivityState())
    internal val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            connectivityService.observeConnectivity().collect { isConnected ->
                if (isConnected) {
                    if (state.value.isDisconnected) {
                        _state.update { it.copyToConnect() }
                        delay(CONNECTED_STATE_DURATION)
                        _state.update { it.reset() }
                    }
                }
            else _state.update { it.copyToDisconnect() }
            }
        }
    }

    companion object {
        private const val CONNECTED_STATE_DURATION = 5_000L
    }
}
