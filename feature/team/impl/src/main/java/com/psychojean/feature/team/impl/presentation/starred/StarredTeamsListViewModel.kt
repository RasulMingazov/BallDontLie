package com.psychojean.feature.team.impl.presentation.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.domain.list.TeamsResult
import com.psychojean.feature.team.impl.domain.starred.StarredTeamsListInteractor
import com.psychojean.feature.team.impl.presentation.list.TeamsListUiState
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StarredTeamsListViewModel @Inject constructor(
    private val teamsListInteractor: StarredTeamsListInteractor,
    private val teamEntityToModelMapper: TeamEntityToModelMapper
) : ViewModel() {

    private val _state = MutableStateFlow(TeamsListUiState())
    val state = _state.asStateFlow()

    fun load() = viewModelScope.launch { fetch() }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.toRetry(errorType) }
        fetch()
    }

    fun removeFromStarred(teamId: Int) = viewModelScope.launch {
        teamsListInteractor.removeFromStarred(teamId)
        fetch()
    }

    private suspend fun fetch() = _state.update { uiState ->
        when (val teamsResult = teamsListInteractor.teams()) {
            is TeamsResult.Error -> uiState.toError(teamsResult.errorType)
            is TeamsResult.Empty -> uiState.toEmpty()
            is TeamsResult.Success -> uiState.toTeams(
                teamsResult.teams.map(teamEntityToModelMapper::map)
            )
        }
    }
}