package com.psychojean.feature.team.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.domain.list.TeamsListInteractor
import com.psychojean.feature.team.impl.domain.list.TeamsResult
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
    private val teamEntityToModelMapper: TeamEntityToModelMapper
) : ViewModel() {

    private val _state = MutableStateFlow(TeamsListUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<TeamsListEvent>()
    val event = _event.receiveAsFlow()

    fun load() = viewModelScope.launch { fetch() }

    fun refresh() = viewModelScope.launch {
        _event.send(TeamsListEvent.Refresh)
        fetch()
        _event.send(TeamsListEvent.EndRefresh)
    }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.toRetry(errorType) }
        fetch()
    }

    fun star(teamId: Int, isStarred: Boolean) = viewModelScope.launch {
        teamsListInteractor.star(teamId, isStarred)
        _state.value.teams?.onEach { team -> if (team.id == teamId) team.isStarred = isStarred }
    }

    private suspend fun fetch() = _state.update { uiState ->
        when (val teamsResult = teamsListInteractor.teams()) {
            is TeamsResult.Error -> uiState.toError(teamsResult.errorType)
            is TeamsResult.Success -> uiState.toTeams(
                teamsResult.teams.map(teamEntityToModelMapper::map)
            )
            is TeamsResult.Empty -> uiState.toEmpty()
        }
    }
}