package com.psychojean.feature.team.impl.domain.starred

import com.psychojean.feature.team.impl.domain.core.TeamsInteractor

internal interface StarredTeamsListInteractor: TeamsInteractor {

    suspend fun removeFromStarred(teamId: Int)

}