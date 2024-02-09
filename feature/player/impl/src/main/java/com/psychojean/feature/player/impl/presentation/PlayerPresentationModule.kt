package com.psychojean.feature.player.impl.presentation

import com.psychojean.feature.player.impl.presentation.detail.DefaultPlayerToUi
import com.psychojean.feature.player.impl.presentation.detail.PlayerToUi
import com.psychojean.feature.player.impl.presentation.model.DefaultPlayerEntityToModelMapper
import com.psychojean.feature.player.impl.presentation.model.PlayerEntityToModelMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface PlayerPresentationModule {

    @Binds
    fun bindPlayerEntityToModelMapper(
        playerEntityToModelMapper: DefaultPlayerEntityToModelMapper
    ): PlayerEntityToModelMapper

    @Binds
    fun bindPlayerToUi(
        starredTeamsToUi: DefaultPlayerToUi
    ): PlayerToUi

}