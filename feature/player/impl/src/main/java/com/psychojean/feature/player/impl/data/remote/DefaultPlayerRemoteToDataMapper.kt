package com.psychojean.feature.player.impl.data.remote

import com.psychojean.feature.player.api.data.model.height.HeightData
import com.psychojean.feature.player.api.data.model.player.PlayerData
import com.psychojean.feature.player.api.data.model.position.PositionData
import com.psychojean.feature.player.api.data.model.weight.WeightData
import com.psychojean.feature.player.api.data.remote.PlayerRemoteToDataMapper
import com.psychojean.feature.player.api.data.remote.model.PlayerRemote
import com.psychojean.feature.team.api.data.remote.TeamRemoteToDataMapper
import kotlin.math.roundToInt

internal class DefaultPlayerRemoteToDataMapper(
    private val teamRemoteToDataMapper: TeamRemoteToDataMapper
) : PlayerRemoteToDataMapper {

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

        PlayerData(
            id,
            "$firstName $lastName",
            playerPosition,
            teamRemoteToDataMapper.map(team),
            playerHeight,
            playerWeight
        )
    }

    companion object {
        private const val ONE_FEET_IN_CM = 30.48
        private const val ONE_POUND_IN_KG = 0.453592
    }
}