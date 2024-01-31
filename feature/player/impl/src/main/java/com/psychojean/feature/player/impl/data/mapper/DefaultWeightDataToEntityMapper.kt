package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.weight.WeightData
import com.psychojean.feature.player.api.data.model.weight.WeightDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.WeightEntity

internal class DefaultWeightDataToEntityMapper : WeightDataToEntityMapper {

    override fun map(item: WeightData): WeightEntity =
        if (item is WeightData.Exist) WeightEntity.Exist(item.weightPounds, item.weightKg) else
            WeightEntity.NotExist
}