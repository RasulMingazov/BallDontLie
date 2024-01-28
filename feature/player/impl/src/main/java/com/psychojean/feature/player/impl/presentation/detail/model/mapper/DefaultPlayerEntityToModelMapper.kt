package com.psychojean.feature.player.impl.presentation.detail.model.mapper

import com.psychojean.core.api.TextProvider
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerTeamModel
import javax.inject.Inject
import kotlin.math.roundToInt

class DefaultPlayerEntityToModelMapper @Inject constructor(
    private val textProvider: TextProvider
) : PlayerEntityToModelMapper {

    override fun map(item: PlayerEntity): PlayerModel = with(item) {
        val heightInCm = (heightFeet * ONE_FEET_IN_CM).roundToInt()
        val weightInKg = (weightPounds * ONE_POUND_IN_KG).roundToInt()

        val heightInCmText = textProvider.getString(R.string.cm, heightInCm.toString())
        val weightInKgText = textProvider.getString(R.string.kg, weightInKg.toString())

        val heightInFeetText = textProvider.getString(R.string.feet, heightFeet.toString())
        val weightInPoundsText = textProvider.getString(R.string.pounds, weightPounds.toString())

        PlayerModel(
            id,
            "$firstName $lastName",
            "$heightInFeetText, $heightInCmText",
            "$weightInPoundsText, $weightInKgText",
            position,
            PlayerTeamModel(
                team.id,
                "${team.fullName} (${team.abbreviation})"
            )
        )
    }

    companion object {
        private const val ONE_FEET_IN_CM = 30.48
        private const val ONE_POUND_IN_KG = 0.453592
    }
}