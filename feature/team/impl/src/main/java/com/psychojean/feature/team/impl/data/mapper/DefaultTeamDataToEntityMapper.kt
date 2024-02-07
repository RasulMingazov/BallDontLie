package com.psychojean.feature.team.impl.data.mapper

import com.psychojean.feature.team.api.data.model.TeamData
import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.domain.model.TeamEntity

internal object DefaultTeamDataToEntityMapper :
    TeamDataToEntityMapper {

    override fun map(item: TeamData): TeamEntity = with(item) {
        TeamEntity(
            id,
            name,
            division,
            abbreviation,
            conference,
            city,
            isStarred
        )
    }
}