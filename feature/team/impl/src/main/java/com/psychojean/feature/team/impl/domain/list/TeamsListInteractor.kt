package com.psychojean.feature.team.impl.domain.list

import com.psychojean.feature.team.impl.domain.core.TeamsInteractor

internal interface TeamsListInteractor : TeamsInteractor {

    suspend fun star(teamId: Int, isStarred: Boolean)

}