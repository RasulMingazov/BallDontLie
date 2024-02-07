package com.psychojean.feature.team.impl.data.local.mapper

import com.psychojean.core.impl.data.database.team.model.TeamLocal
import com.psychojean.feature.team.api.data.model.TeamData

object DefaultTeamDataToLocalMapper: TeamDataToLocalMapper {

    override fun map(item: TeamData): TeamLocal = with(item) {
        TeamLocal(id, name, division, abbreviation, conference, city, isStarred)
    }
}