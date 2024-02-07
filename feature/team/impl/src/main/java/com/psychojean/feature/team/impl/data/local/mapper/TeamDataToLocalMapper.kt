package com.psychojean.feature.team.impl.data.local.mapper

import com.psychojean.core.api.Mapper
import com.psychojean.core.impl.data.database.team.model.TeamLocal
import com.psychojean.feature.team.api.data.model.TeamData

interface TeamDataToLocalMapper: Mapper<TeamData, TeamLocal>