package com.psychojean.feature.player.api.data.model.team

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.domain.detail.model.TeamEntity

interface TeamDataToEntityMapper: Mapper<TeamData, TeamEntity>