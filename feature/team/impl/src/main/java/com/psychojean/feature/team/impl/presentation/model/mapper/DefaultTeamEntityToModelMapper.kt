package com.psychojean.feature.team.impl.presentation.model.mapper

import com.psychojean.feature.team.api.domain.model.TeamEntity
import com.psychojean.feature.team.impl.presentation.model.TeamModel

internal object DefaultTeamEntityToModelMapper :
    TeamEntityToModelMapper {

    override fun map(item: TeamEntity): TeamModel = with(item) {
        TeamModel(
            id,
            "$name ($abbreviation)",
            division,
            abbreviation,
            conference,
            city,
            isStarred
        )
    }
}