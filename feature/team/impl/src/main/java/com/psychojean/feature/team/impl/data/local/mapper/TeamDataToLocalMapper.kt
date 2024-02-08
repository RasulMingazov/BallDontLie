package com.psychojean.feature.team.impl.data.local.mapper

import com.psychojean.core.api.Mapper
import com.psychojean.core.impl.data.database.team.model.TeamLocal
import com.psychojean.feature.team.api.data.model.TeamData
import javax.inject.Inject

interface TeamDataToLocalMapper: Mapper<TeamData, TeamLocal>

class DefaultTeamDataToLocalMapper @Inject constructor() : TeamDataToLocalMapper {

    override fun map(item: TeamData): TeamLocal = with(item) {
        TeamLocal(id, name, division, abbreviation, conference, city, isStarred)
    }
}