package com.psychojean.feature.team.impl.presentation.model.mapper

import com.psychojean.core.api.Mapper
import com.psychojean.feature.team.api.domain.model.TeamEntity
import com.psychojean.feature.team.impl.presentation.model.TeamModel

internal interface TeamEntityToModelMapper: Mapper<TeamEntity, TeamModel>