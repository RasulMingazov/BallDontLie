package com.psychojean.feature.team.impl.domain.starred

import com.psychojean.core.api.error.ErrorType
import com.psychojean.feature.team.api.domain.model.TeamEntity

internal sealed interface StarredTeamsResult {

    data class Success(val teams: List<TeamEntity>): StarredTeamsResult

    data class Error(val errorType: ErrorType): StarredTeamsResult

    data object Empty: StarredTeamsResult

}