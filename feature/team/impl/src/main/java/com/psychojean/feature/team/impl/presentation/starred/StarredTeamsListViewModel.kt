package com.psychojean.feature.team.impl.presentation.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.impl.domain.starred.StarredTeamsListInteractor
import com.psychojean.feature.team.impl.domain.starred.StarredTeamsResult
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

    private val _state = MutableStateFlow(StarredTeamsListUiState())
    val state = _state.asStateFlow()

    fun load() = viewModelScope.launch { fetchStarredTeams() }

    fun retry(errorType: ErrorType) = viewModelScope.launch {
        _state.update { uiState -> uiState.copyToRetry(errorType) }
        fetchStarredTeams()
    }

    fun removeFromStarred(teamId: Int) = viewModelScope.launch {
        if (teamsListInteractor.removeFromStarred(teamId).isSuccess) fetchStarredTeams()
    }

    private suspend fun fetchStarredTeams() = _state.update { uiState ->
        when (val teamsResult = teamsListInteractor.starredTeams()) {
            is StarredTeamsResult.Error -> uiState.copyToError(teamsResult.errorType)
            is StarredTeamsResult.Empty -> uiState.copyToEmpty()
            is StarredTeamsResult.Success -> uiState.copyToTeams(
                teamsResult.teams.map(teamEntityToModelMapper::map)
            )
        }
    }
}