package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.abbreviation.AbbreviationData
import com.psychojean.feature.player.api.data.model.city.CityData
import com.psychojean.feature.player.api.data.model.conference.ConferenceData
import com.psychojean.feature.player.api.data.model.division.DivisionData
import com.psychojean.feature.player.api.data.model.height.HeightData
import com.psychojean.feature.player.api.data.model.name.NameData
import com.psychojean.feature.player.api.data.model.player.PlayerData
import com.psychojean.feature.player.api.data.model.position.PositionData
import com.psychojean.feature.player.api.data.model.team.TeamData
import com.psychojean.feature.player.api.data.model.weight.WeightData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote
import kotlin.math.roundToInt

internal class DefaultPlayerRemoteToDataMapper : PlayerRemoteToDataMapper {

    override fun map(item: PlayerRemote): PlayerData = with(item) {
        var playerName: NameData = NameData.NotExist
        if (firstName == null && lastName != null) playerName = NameData.Exist(lastName!!)
        if (firstName != null && lastName == null) playerName = NameData.Exist(firstName!!)
        if (firstName != null && lastName != null) playerName =
            NameData.Exist("$firstName $lastName")

        val playerPosition =
            if (position != null && position!!.isNotEmpty()) PositionData.Exist(position!!) else PositionData.NotExist
        val playerHeight = if (heightFeet != null) HeightData.Exist(
            (heightFeet!! * ONE_FEET_IN_CM).roundToInt(),
            heightFeet!!
        ) else HeightData.NotExist
        val playerWeight = if (weightPounds != null) WeightData.Exist(
            weightPounds!!,
            (weightPounds!! * ONE_POUND_IN_KG).roundToInt()
        ) else WeightData.NotExist

        val playerTeam: TeamData = if (team != null) TeamData.Exist(
            team!!.id,
            if (team!!.fullName != null && team!!.fullName!!.isNotEmpty()) NameData.Exist(team!!.fullName!!) else NameData.NotExist,
            if (team!!.division != null && team!!.division!!.isNotEmpty()) DivisionData.Exist(team!!.division!!) else DivisionData.NotExist,
            if (team!!.abbreviation != null && team!!.abbreviation!!.isNotEmpty()) AbbreviationData.Exist(
                team!!.abbreviation!!
            ) else AbbreviationData.NotExist,
            if (team!!.conference != null && team!!.conference!!.isNotEmpty()) ConferenceData.Exist(
                team!!.conference!!
            ) else ConferenceData.NotExist,
            if (team!!.city != null && team!!.city!!.isNotEmpty()) CityData.Exist(team!!.city!!) else CityData.NotExist,

            ) else TeamData.NotExist
        PlayerData(
            id,
            playerName,
            playerPosition,
            playerTeam,
            playerHeight,
            playerWeight
        )
    }

    companion object {
        private const val ONE_FEET_IN_CM = 30.48
        private const val ONE_POUND_IN_KG = 0.453592
    }
}