package com.psychojean.feature.player.impl.data.mapper

import com.psychojean.feature.player.api.data.model.abbreviation.AbbreviationDataToEntityMapper
import com.psychojean.feature.player.api.data.model.city.CityDataToEntityMapper
import com.psychojean.feature.player.api.data.model.conference.ConferenceDataToEntityMapper
import com.psychojean.feature.player.api.data.model.division.DivisionDataToEntityMapper
import com.psychojean.feature.player.api.data.model.name.NameDataToEntityMapper
import com.psychojean.feature.player.api.data.model.team.TeamData
import com.psychojean.feature.player.api.data.model.team.TeamDataToEntityMapper
import com.psychojean.feature.player.api.domain.detail.model.TeamEntity

internal class DefaultTeamDataToEntityMapper(
    private val nameDataToEntityMapper: NameDataToEntityMapper,
    private val divisionDataToEntityMapper: DivisionDataToEntityMapper,
    private val abbreviationDataToEntityMapper: AbbreviationDataToEntityMapper,
    private val conferenceDataToEntityMapper: ConferenceDataToEntityMapper,
    private val cityDataToEntityMapper: CityDataToEntityMapper

) : TeamDataToEntityMapper {

    override fun map(item: TeamData): TeamEntity = with(item) {
        if (this is TeamData.NotExist) return@with TeamEntity.NotExist
        TeamEntity.Exist(
            (this as TeamData.Exist).id,
            nameDataToEntityMapper.map(name),
            divisionDataToEntityMapper.map(division),
            abbreviationDataToEntityMapper.map(abbreviation),
            conferenceDataToEntityMapper.map(conference),
            cityDataToEntityMapper.map(city)
        )
    }
}