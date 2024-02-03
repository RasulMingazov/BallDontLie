package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.height.HeightData
import com.psychojean.feature.player.api.data.model.player.PlayerData
import com.psychojean.feature.player.api.data.model.position.PositionData
import com.psychojean.feature.player.api.data.model.team.TeamData
import com.psychojean.feature.player.api.data.model.weight.WeightData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote
import kotlin.math.roundToInt

internal class DefaultPlayerRemoteToDataMapper : PlayerRemoteToDataMapper {

    override fun map(item: PlayerRemote): PlayerData = with(item) {
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

        val playerTeam = TeamData(
            team.id,
            team.fullName,
            team.division,
            team.abbreviation,
            team.conference,
            team.city
        )
        PlayerData(
            id,
            "$firstName $lastName",
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