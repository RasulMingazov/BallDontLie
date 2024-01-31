package com.psychojean.feature.player.impl.data

import com.psychojean.feature.player.api.data.model.abbreviation.AbbreviationDataToEntityMapper
import com.psychojean.feature.player.api.data.model.city.CityDataToEntityMapper
import com.psychojean.feature.player.api.data.model.conference.ConferenceDataToEntityMapper
import com.psychojean.feature.player.api.data.model.division.DivisionDataToEntityMapper
import com.psychojean.feature.player.api.data.model.height.HeightDataToEntityMapper
import com.psychojean.feature.player.api.data.model.name.NameDataToEntityMapper
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.model.position.PositionDataToEntityMapper
import com.psychojean.feature.player.api.data.model.team.TeamDataToEntityMapper
import com.psychojean.feature.player.api.data.model.weight.WeightDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.impl.data.mapper.DefaultAbbreviationDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultCityDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultConferenceDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultDivisionDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultHeightDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultNameDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultPlayerDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultPositionDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultTeamDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultWeightDataToEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class PlayerDataModule {

    @Provides
    fun provideNameDataToEntityMapper(): NameDataToEntityMapper =
        DefaultNameDataToEntityMapper()

    @Provides
    fun providePositionDataToEntityMapper(): PositionDataToEntityMapper =
        DefaultPositionDataToEntityMapper()

    @Provides
    fun provideHeightDataToEntityMapper(): HeightDataToEntityMapper =
        DefaultHeightDataToEntityMapper()

    @Provides
    fun provideWeightDataToEntityMapper(): WeightDataToEntityMapper =
        DefaultWeightDataToEntityMapper()

    @Provides
    fun provideDivisionDataToEntityMapper(): DivisionDataToEntityMapper =
        DefaultDivisionDataToEntityMapper()

    @Provides
    fun provideAbbreviationDataToEntityMapper(): AbbreviationDataToEntityMapper =
        DefaultAbbreviationDataToEntityMapper()

    @Provides
    fun provideConferenceDataToEntityMapper(): ConferenceDataToEntityMapper =
        DefaultConferenceDataToEntityMapper()

    @Provides
    fun provideCityDataToEntityMapper(): CityDataToEntityMapper =
        DefaultCityDataToEntityMapper()

    @Provides
    fun provideTeamDataToEntityMapper(
        nameDataToEntityMapper: NameDataToEntityMapper,
        divisionDataToEntityMapper: DivisionDataToEntityMapper,
        abbreviationDataToEntityMapper: AbbreviationDataToEntityMapper,
        conferenceDataToEntityMapper: ConferenceDataToEntityMapper,
        cityDataToEntityMapper: CityDataToEntityMapper
    ): TeamDataToEntityMapper =
        DefaultTeamDataToEntityMapper(
            nameDataToEntityMapper,
            divisionDataToEntityMapper,
            abbreviationDataToEntityMapper,
            conferenceDataToEntityMapper,
            cityDataToEntityMapper
        )

    @Provides
    fun providePlayerDataToEntityMapper(
        nameDataToEntityMapper: NameDataToEntityMapper,
        positionDataToEntityMapper: PositionDataToEntityMapper,
        heightDataToEntityMapper: HeightDataToEntityMapper,
        weightDataToEntityMapper: WeightDataToEntityMapper,
        teamDataToEntityMapper: TeamDataToEntityMapper
    ): PlayerDataToEntityMapper =
        DefaultPlayerDataToEntityMapper(
            nameDataToEntityMapper,
            positionDataToEntityMapper,
            heightDataToEntityMapper,
            weightDataToEntityMapper,
            teamDataToEntityMapper
        )


    @Provides
    fun providePlayerRepository(
        playerRemoteDataSource: PlayerRemoteDataSource,
        playerDataToEntityMapper: PlayerDataToEntityMapper
    ): PlayerRepository = DefaultPlayerRepository(
        playerRemoteDataSource, playerDataToEntityMapper
    )
}
