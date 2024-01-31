package com.psychojean.feature.player.api.data.model.city

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.domain.detail.model.CityEntity

interface CityDataToEntityMapper: Mapper<CityData, CityEntity>