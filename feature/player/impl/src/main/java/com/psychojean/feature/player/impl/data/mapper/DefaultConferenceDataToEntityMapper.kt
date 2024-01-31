package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.conference.ConferenceData
import com.psychojean.feature.player.api.data.model.conference.ConferenceDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.ConferenceEntity

internal class DefaultConferenceDataToEntityMapper : ConferenceDataToEntityMapper {

    override fun map(item: ConferenceData): ConferenceEntity =
        if (item is ConferenceData.Exist) ConferenceEntity.Exist(item.conference) else
            ConferenceEntity.NotExist
}