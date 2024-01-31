package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.name.NameData
import com.psychojean.feature.player.api.data.model.name.NameDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.NameEntity

internal class DefaultNameDataToEntityMapper : NameDataToEntityMapper {

    override fun map(item: NameData): NameEntity =
        if (item is NameData.Exist) NameEntity.Exist(item.fullName) else
            NameEntity.NotExist
}