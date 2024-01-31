package com.psychojean.feature.player.api.domain.detail.model

sealed class TeamEntity {

    data class Exist(
        val id: Int,
        val name: NameEntity,
        val division: DivisionEntity,
        val abbreviation: AbbreviationEntity,
        val conference: ConferenceEntity,
        val city: CityEntity
    ): TeamEntity()

    data object NotExist: TeamEntity()
}

