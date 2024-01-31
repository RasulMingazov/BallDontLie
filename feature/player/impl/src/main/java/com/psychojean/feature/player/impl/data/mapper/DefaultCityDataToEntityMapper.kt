package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.city.CityData
import com.psychojean.feature.player.api.data.model.city.CityDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.CityEntity

internal class DefaultCityDataToEntityMapper : CityDataToEntityMapper {

    override fun map(item: CityData): CityEntity =
        if (item is CityData.Exist) CityEntity.Exist(item.city) else
            CityEntity.NotExist
}