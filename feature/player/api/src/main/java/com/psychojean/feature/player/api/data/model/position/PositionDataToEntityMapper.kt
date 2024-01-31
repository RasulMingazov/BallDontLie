package com.psychojean.feature.player.api.data.model.position

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.domain.detail.model.PositionEntity

interface PositionDataToEntityMapper: Mapper<PositionData, PositionEntity>