package com.psychojean.feature.player.api.data.model.conference

import com.psychojean.core.api.Mapper
import com.psychojean.feature.player.api.domain.detail.model.ConferenceEntity

interface ConferenceDataToEntityMapper: Mapper<ConferenceData, ConferenceEntity>