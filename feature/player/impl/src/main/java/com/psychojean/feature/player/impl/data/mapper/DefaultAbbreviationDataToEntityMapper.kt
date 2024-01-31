package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.abbreviation.AbbreviationData
import com.psychojean.feature.player.api.data.model.abbreviation.AbbreviationDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.AbbreviationEntity

internal class DefaultAbbreviationDataToEntityMapper : AbbreviationDataToEntityMapper {

    override fun map(item: AbbreviationData): AbbreviationEntity =
        if (item is AbbreviationData.Exist) AbbreviationEntity.Exist(item.abbreviation) else
            AbbreviationEntity.NotExist
}