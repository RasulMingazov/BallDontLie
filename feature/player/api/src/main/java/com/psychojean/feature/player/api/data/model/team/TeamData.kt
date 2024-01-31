package com.psychojean.feature.player.api.data.model.team

import com.psychojean.feature.player.api.data.model.abbreviation.AbbreviationData
import com.psychojean.feature.player.api.data.model.city.CityData
import com.psychojean.feature.player.api.data.model.conference.ConferenceData
import com.psychojean.feature.player.api.data.model.division.DivisionData
import com.psychojean.feature.player.api.data.model.name.NameData


sealed class TeamData {

    data class Exist(
        val id: Int,
        val name: NameData,
        val division: DivisionData,
        val abbreviation: AbbreviationData,
        val conference: ConferenceData,
        val city: CityData
    ): TeamData()

    data object NotExist: TeamData()
}
