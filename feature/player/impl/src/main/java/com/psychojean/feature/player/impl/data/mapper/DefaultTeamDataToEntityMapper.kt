package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.team.TeamData
import com.psychojean.feature.player.api.data.model.team.TeamDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.TeamEntity

internal object DefaultTeamDataToEntityMapper : TeamDataToEntityMapper {

    override fun map(item: TeamData): TeamEntity = with(item) {
        TeamEntity(
            id,
            name,
            division,
            abbreviation,
            conference,
            city
        )
    }
}