package com.psychojean.feature.player.impl.presentation.detail.model.mapper

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel

internal interface PlayerEntityToModelMapper: Mapper<PlayerEntity, PlayerModel>