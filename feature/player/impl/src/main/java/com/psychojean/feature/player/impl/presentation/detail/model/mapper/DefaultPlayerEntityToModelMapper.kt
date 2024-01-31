package com.psychojean.feature.player.impl.presentation.detail.model.mapper

import com.psychojean.core.api.TextProvider
import com.psychojean.feature.player.api.domain.detail.model.HeightEntity
import com.psychojean.feature.player.api.domain.detail.model.NameEntity
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import com.psychojean.feature.player.api.domain.detail.model.PositionEntity
import com.psychojean.feature.player.api.domain.detail.model.TeamEntity
import com.psychojean.feature.player.api.domain.detail.model.WeightEntity
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel
import javax.inject.Inject

internal class DefaultPlayerEntityToModelMapper @Inject constructor(
    private val textProvider: TextProvider
) : PlayerEntityToModelMapper {

    override fun map(item: PlayerEntity): PlayerModel = with(item) {

        val heightText = if (height is HeightEntity.Exist)
            "${textProvider.getString(R.string.feet, (height as HeightEntity.Exist).heightFeet)
            }, ${textProvider.getString(R.string.cm, (height as HeightEntity.Exist).heightCm)}"
        else "-"

        val weightText = if (weight is WeightEntity.Exist)
            "${textProvider.getString(R.string.pounds, (weight as WeightEntity.Exist).weightPounds)}," +
                    " ${textProvider.getString(R.string.kg, (weight as WeightEntity.Exist).weightKg)}"
        else "-"
        val nameText = (name as? NameEntity.Exist)?.fullName ?: "-"
        val positionText = (position as? PositionEntity.Exist)?.position ?: "-"

        val teamId = (team as? TeamEntity.Exist)?.id ?: -1
        val teamName: String = ((team as? TeamEntity.Exist)?.name as? NameEntity.Exist)?.fullName ?: "-"

        PlayerModel(
            id,
            nameText,
            heightText,
            weightText,
            positionText,
            teamId,
            teamName
        )
    }
}