package com.psychojean.feature.player.api.data.model.player

import com.psychojean.feature.player.api.data.model.height.HeightData
import com.psychojean.feature.player.api.data.model.position.PositionData
import com.psychojean.feature.player.api.data.model.weight.WeightData
import com.psychojean.feature.team.api.data.model.TeamData

data class PlayerData(
    val id: Int,
    val name: String,
    val position: PositionData,
    val team: TeamData,
    val height: HeightData,
    val weight: WeightData,
)
