package com.psychojean.feature.team.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.exceptionOrThrow
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.core.impl.presentation.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.team.impl.domain.list.TeamsListInteractor
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TeamsListViewModel @Inject constructor(
    private val teamsListInteractor: TeamsListInteractor,
    private val teamEntityToModelMapper: TeamEntityToModelMapper,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper
) : ViewModel() {

    private val _state = MutableStateFlow(TeamsListUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<TeamsListEvent>()
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch { fetch() }
    }

    fun refresh() = viewModelScope.launch {
        _event.send(TeamsListEvent.Refresh)
        fetch()
    }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.toRetry(errorType) }
        fetch()
    }

    private suspend fun fetch() {
        val teamsResult = teamsListInteractor.teams()
        if (teamsResult.isSuccess) {
            _state.update { uiState ->
                uiState.toTeams(teamsResult.getOrThrow().map(teamEntityToModelMapper::map))
            }
            _event.send(TeamsListEvent.EndRefresh)
        } else _state.update { uiState -> uiState.toError(errorTypeMapper.map(teamsResult.exceptionOrThrow())) }

    }
}