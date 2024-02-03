package com.psychojean.feature.player.impl.data

import com.psychojean.feature.player.api.data.model.height.HeightDataToEntityMapper
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.model.position.PositionDataToEntityMapper
import com.psychojean.feature.player.api.data.model.team.TeamDataToEntityMapper
import com.psychojean.feature.player.api.data.model.weight.WeightDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.impl.data.mapper.DefaultHeightDataToEntityMapper
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
    fun providePositionDataToEntityMapper(): PositionDataToEntityMapper =
        DefaultPositionDataToEntityMapper()

    @Provides
    fun provideHeightDataToEntityMapper(): HeightDataToEntityMapper =
        DefaultHeightDataToEntityMapper()

    @Provides
    fun provideWeightDataToEntityMapper(): WeightDataToEntityMapper =
        DefaultWeightDataToEntityMapper()

    @Provides
    fun provideTeamDataToEntityMapper(
    ): TeamDataToEntityMapper =
        DefaultTeamDataToEntityMapper

    @Provides
    fun providePlayerDataToEntityMapper(
        positionDataToEntityMapper: PositionDataToEntityMapper,
        heightDataToEntityMapper: HeightDataToEntityMapper,
        weightDataToEntityMapper: WeightDataToEntityMapper,
        teamDataToEntityMapper: TeamDataToEntityMapper
    ): PlayerDataToEntityMapper =
        DefaultPlayerDataToEntityMapper(
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
