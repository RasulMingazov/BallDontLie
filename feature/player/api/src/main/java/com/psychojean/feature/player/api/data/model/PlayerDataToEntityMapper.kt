package com.psychojean.feature.player.api.data.model

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity

interface PlayerDataToEntityMapper: Mapper<PlayerData, PlayerEntity>