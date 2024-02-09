package com.psychojean.feature.team.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.domain.list.TeamsListInteractor
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
    private val teamsToUi: TeamsToUi
) : ViewModel() {

    private val _state = MutableStateFlow(TeamsListUiState())
    val state = _state.asStateFlow()

    private val _event = Channel<TeamsListEvent>()
    val event = _event.receiveAsFlow()

    fun load() = viewModelScope.launch { fetchTeams() }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.copyToRetry(errorType) }
        fetchTeams()
    }

    fun refresh() = viewModelScope.launch {
        _event.send(TeamsListEvent.Refresh)
        fetchTeams()
        _event.send(TeamsListEvent.EndRefresh)
    }

    fun star(teamId: Int, isStarred: Boolean) = viewModelScope.launch {
        if (teamsListInteractor.star(teamId, isStarred).isSuccess) fetchTeams()
    }

    private suspend fun fetchTeams() = _state.update { uiState ->
        teamsToUi.run { uiState.copyFromResult(teamsListInteractor.teams()) }
    }
}
