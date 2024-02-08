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
internal abstract class PlayerDataModule {

    @Binds
    abstract fun bindPlayerRepository(
        playerRepository: DefaultPlayerRepository
    ): PlayerRepository

    @Binds
    abstract fun bindPositionDataToEntityMapper(
        positionDataToEntityMapper: DefaultPositionDataToEntityMapper
    ): PositionDataToEntityMapper

    @Binds
    abstract fun bindHeightDataToEntityMapper(
        heightDataToEntityMapper: DefaultHeightDataToEntityMapper
    ): HeightDataToEntityMapper

    @Binds
    abstract fun bindWeightDataToEntityMapper(
        weightDataToEntityMapper: DefaultWeightDataToEntityMapper
    ): WeightDataToEntityMapper

    @Binds
    abstract fun bindPlayerDataToEntityMapper(
        playerDataToEntityMapper: DefaultPlayerDataToEntityMapper
    ): PlayerDataToEntityMapper
}
