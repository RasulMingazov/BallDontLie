package com.psychojean.feature.player.impl.presentation.detail.model.mapper

import com.psychojean.core.api.TextProvider
import com.psychojean.feature.player.api.domain.detail.model.PlayerEntity
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerTeamModel
import javax.inject.Inject
import kotlin.math.roundToInt

internal class DefaultPlayerEntityToModelMapper @Inject constructor(
    private val textProvider: TextProvider
) : PlayerEntityToModelMapper {

    override fun map(item: PlayerEntity): PlayerModel = with(item) {
        val heightText = heightFeet?.let { height ->
            val heightInCm = (height * ONE_FEET_IN_CM).roundToInt()
            val heightInCmText = textProvider.getString(R.string.cm, heightInCm.toString())
            val heightInFeetText = textProvider.getString(R.string.feet, heightFeet.toString())
            "$heightInFeetText, $heightInCmText"
        } ?: "-"

        val weightText = weightPounds?.let { weight ->
            val weightInKg = (weight * ONE_POUND_IN_KG).roundToInt()
            val weightInKgText = textProvider.getString(R.string.kg, weightInKg.toString())
            val weightInPoundsText =
                textProvider.getString(R.string.pounds, weightPounds.toString())
            "$weightInPoundsText, $weightInKgText"
        } ?: "-"

        val team = team?.let { team ->
            PlayerTeamModel(team.id, "${team.fullName ?: "-"} ${team.abbreviation ?: ""}")
        } ?: PlayerTeamModel(-1, "-")

        PlayerModel(
            id,
            "${firstName ?: ""} ${lastName ?: ""}",
            heightText,
            weightText,
            position ?: "-",
            team
        )
    }

    companion object {
        private const val ONE_FEET_IN_CM = 30.48
        private const val ONE_POUND_IN_KG = 0.453592
    }
}