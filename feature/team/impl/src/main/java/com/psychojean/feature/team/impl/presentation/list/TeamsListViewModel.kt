package com.psychojean.feature.team.impl.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.psychojean.core.api.Dispatcher
import com.psychojean.core.impl.presentation.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.di.ErrorQualifier
import com.psychojean.feature.team.api.domain.list.TeamsListInteractor
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class TeamsListViewModel @Inject constructor(
    private val teamsListInteractor: TeamsListInteractor,
    private val teamEntityToModelMapper: TeamEntityToModelMapper,
    @ErrorQualifier private val errorTypeMapper: ErrorTypeMapper,
    private val dispatcher: Dispatcher
) : ViewModel() {

    private val _state = MutableStateFlow<TeamsListState>(TeamsListState.Loading)
    val state = _state.asStateFlow()

    private val _event = Channel<TeamsListEvent>()
    val event = _event.receiveAsFlow()

    init {
        dispatcher.launchBackground(viewModelScope) {
            fetch().collect()
        }
    }

    fun refresh() {
        dispatcher.launchBackground(viewModelScope) {
            fetch().onStart { _event.send(TeamsListEvent.Refresh) }.collect()
        }
    }

    fun retry() {
        dispatcher.launchBackground(viewModelScope) {
            fetch().onStart { _state.value = TeamsListState.Loading }.collect()
        }
    }

    private fun fetch() = teamsListInteractor.players()
        .map { it.map(teamEntityToModelMapper::map) }
        .onEach {
            _state.value = TeamsListState.Success(it)
            _event.send(TeamsListEvent.EndRefresh)
        }
        .catch { _state.value = TeamsListState.Error(errorTypeMapper.map(it)) }
}