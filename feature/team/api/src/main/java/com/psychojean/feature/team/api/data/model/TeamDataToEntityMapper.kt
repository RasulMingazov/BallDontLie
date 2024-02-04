package com.psychojean.feature.team.api.data.model

import com.psychojean.core.api.Mapper
import com.psychojean.feature.team.api.domain.model.TeamEntity

interface TeamDataToEntityMapper: Mapper<TeamData, TeamEntity>