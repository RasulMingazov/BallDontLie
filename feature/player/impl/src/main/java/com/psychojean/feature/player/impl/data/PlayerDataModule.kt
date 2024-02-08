package com.psychojean.feature.player.impl.data

import com.psychojean.feature.player.api.data.model.height.HeightDataToEntityMapper
import com.psychojean.feature.player.api.data.model.player.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.model.position.PositionDataToEntityMapper
import com.psychojean.feature.player.api.data.model.weight.WeightDataToEntityMapper
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.impl.data.mapper.DefaultHeightDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultPlayerDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultPositionDataToEntityMapper
import com.psychojean.feature.player.impl.data.mapper.DefaultWeightDataToEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface PlayerDataModule {

    @Binds
    fun bindPlayerRepository(
        playerRepository: DefaultPlayerRepository
    ): PlayerRepository

    @Binds
    fun bindPositionDataToEntityMapper(
        positionDataToEntityMapper: DefaultPositionDataToEntityMapper
    ): PositionDataToEntityMapper

    @Binds
    fun bindHeightDataToEntityMapper(
        heightDataToEntityMapper: DefaultHeightDataToEntityMapper
    ): HeightDataToEntityMapper

    @Binds
    fun bindWeightDataToEntityMapper(
        weightDataToEntityMapper: DefaultWeightDataToEntityMapper
    ): WeightDataToEntityMapper

    @Binds
    fun bindPlayerDataToEntityMapper(
        playerDataToEntityMapper: DefaultPlayerDataToEntityMapper
    ): PlayerDataToEntityMapper
}
