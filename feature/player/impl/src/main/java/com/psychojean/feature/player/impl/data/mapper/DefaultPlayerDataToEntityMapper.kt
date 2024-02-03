package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.height.HeightDataToEntityMapper
import com.psychojean.feature.player.api.data.model.player.PlayerData
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.model.position.PositionDataToEntityMapper
import com.psychojean.feature.player.api.data.model.team.TeamDataToEntityMapper
import com.psychojean.feature.player.api.data.model.weight.WeightDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity

internal class DefaultPlayerDataToEntityMapper(
    private val positionDataToEntityMapper: PositionDataToEntityMapper,
    private val heightDataToEntityMapper: HeightDataToEntityMapper,
    private val weightDataToEntityMapper: WeightDataToEntityMapper,
    private val teamDataToEntityMapper: TeamDataToEntityMapper
) : PlayerDataToEntityMapper {

    override fun map(item: PlayerData): PlayerEntity = with(item) {
        PlayerEntity(
            id,
            name,
            positionDataToEntityMapper.map(position),
            heightDataToEntityMapper.map(height),
            weightDataToEntityMapper.map(weight),
            teamDataToEntityMapper.map(team)
        )
    }
}