package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.position.PositionData
import com.psychojean.feature.player.api.data.model.position.PositionDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.PositionEntity

internal class DefaultPositionDataToEntityMapper : PositionDataToEntityMapper {

    override fun map(item: PositionData): PositionEntity =
        if (item is PositionData.Exist) PositionEntity.Exist(item.position) else
            PositionEntity.NotExist
}