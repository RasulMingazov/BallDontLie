package com.psychojean.feature.player.impl.presentation.di

import com.psychojean.feature.player.impl.presentation.model.DefaultPlayerEntityToModelMapper
import com.psychojean.feature.player.impl.presentation.model.PlayerEntityToModelMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class PlayerPresentationModule {

    @Binds
    abstract fun bindPlayerEntityToModelMapper(
        playerEntityToModelMapper: DefaultPlayerEntityToModelMapper
    ): PlayerEntityToModelMapper

}