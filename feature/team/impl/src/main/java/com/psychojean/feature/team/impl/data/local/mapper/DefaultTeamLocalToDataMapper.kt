package com.psychojean.feature.team.impl.data.local.mapper

import com.psychojean.core.impl.data.database.team.model.TeamLocal
import com.psychojean.feature.team.api.data.model.TeamData

object DefaultTeamLocalToDataMapper: TeamLocalToDataMapper {

    override fun map(item: TeamLocal): TeamData = with(item) {
        TeamData(teamId, name, division, abbreviation, conference, city, isStarred)
    }
}