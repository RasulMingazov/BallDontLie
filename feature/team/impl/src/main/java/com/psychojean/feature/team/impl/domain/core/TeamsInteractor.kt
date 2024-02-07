package com.psychojean.feature.team.impl.domain.core

import com.psychojean.feature.team.impl.domain.list.TeamsResult

internal interface TeamsInteractor {

    suspend fun teams(): TeamsResult

}