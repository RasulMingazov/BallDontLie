package com.psychojean.feature.team.impl.presentation.model.mapper

import com.psychojean.core.api.Mapper
import com.psychojean.feature.team.api.domain.model.TeamEntity
import com.psychojean.feature.team.impl.presentation.model.TeamModel
import javax.inject.Inject

internal interface TeamEntityToModelMapper: Mapper<TeamEntity, TeamModel>

internal class DefaultTeamEntityToModelMapper @Inject constructor() : TeamEntityToModelMapper {

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
