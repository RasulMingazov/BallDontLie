package com.psychojean.feature.team.impl.domain.list

import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.api.domain.model.TeamEntity

sealed interface TeamsResult {

    data class Success(val teams: List<TeamEntity>): TeamsResult

    data class Error(val errorType: ErrorType): TeamsResult

    data object Empty: TeamsResult

}