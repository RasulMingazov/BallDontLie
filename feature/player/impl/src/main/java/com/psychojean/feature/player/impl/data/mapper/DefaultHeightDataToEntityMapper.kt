package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.height.HeightData
import com.psychojean.feature.player.api.data.model.height.HeightDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.HeightEntity
import javax.inject.Inject

internal class DefaultHeightDataToEntityMapper @Inject constructor() : HeightDataToEntityMapper {

    override fun map(item: HeightData): HeightEntity =
        if (item is HeightData.Exist) HeightEntity.Exist(item.heightCm, item.heightFeet) else
            HeightEntity.NotExist
}