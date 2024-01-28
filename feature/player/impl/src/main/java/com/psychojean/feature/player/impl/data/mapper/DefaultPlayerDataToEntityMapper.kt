package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.PlayerData
import com.psychojean.feature.player.api.data.model.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import com.psychojean.feature.player.api.domain.detail.model.TeamEntity

internal class DefaultPlayerDataToEntityMapper: PlayerDataToEntityMapper {

    override fun map(item: PlayerData): PlayerEntity = with(item) {
        PlayerEntity(
            id,
            firstName,
            lastName,
            heightFeet,
            heightInches,
            weightPounds,
            position,
            TeamEntity(
                team.id,
                team.name,
                team.fullName,
                team.division,
                team.abbreviation,
                team.conference,
                team.city
            )
        )
    }
}