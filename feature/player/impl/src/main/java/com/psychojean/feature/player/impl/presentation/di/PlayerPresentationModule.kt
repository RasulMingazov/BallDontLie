package com.psychojean.feature.player.impl.presentation.di

import com.psychojean.core.api.TextProvider
import com.psychojean.feature.player.impl.presentation.detail.model.mapper.DefaultPlayerEntityToModelMapper
import com.psychojean.feature.player.impl.presentation.detail.model.mapper.PlayerEntityToModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class PlayerPresentationModule {

    @Provides
    fun providePlayerEntityToModelMapper(textProvider: TextProvider): PlayerEntityToModelMapper =
        DefaultPlayerEntityToModelMapper(textProvider)

}