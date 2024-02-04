package com.psychojean.feature.team.impl.presentation.list

import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.feature.team.impl.presentation.model.TeamModel
import javax.annotation.concurrent.Immutable

@Immutable
internal sealed class TeamsListState {
    data object Loading : TeamsListState()
    data class Success(val teams: List<TeamModel>) : TeamsListState()
    data class Error(val errorType: ErrorType) : TeamsListState()
}
