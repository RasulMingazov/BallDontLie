package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.division.DivisionData
import com.psychojean.feature.player.api.data.model.division.DivisionDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.DivisionEntity

internal class DefaultDivisionDataToEntityMapper : DivisionDataToEntityMapper {

    override fun map(item: DivisionData): DivisionEntity =
        if (item is DivisionData.Exist) DivisionEntity.Exist(item.division) else
            DivisionEntity.NotExist
}