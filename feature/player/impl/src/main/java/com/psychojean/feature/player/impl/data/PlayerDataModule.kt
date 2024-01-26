package com.psychojean.feature.player.impl.data

import com.psychojean.feature.player.api.data.model.PlayerDataToEntityMapper
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.api.domain.PlayerRepository
import com.psychojean.feature.player.impl.data.mapper.DefaultPlayerDataToEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class PlayerDataModule {

    @Provides
    fun providePlayerDataToEntityMapper(): PlayerDataToEntityMapper =
        DefaultPlayerDataToEntityMapper()

    @Provides
    fun providePlayerRepository(
        playerRemoteDataSource: PlayerRemoteDataSource,
        playerDataToEntityMapper: PlayerDataToEntityMapper
    ): PlayerRepository = DefaultPlayerRepository(
        playerRemoteDataSource, playerDataToEntityMapper
    )
}
