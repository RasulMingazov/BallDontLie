package com.psychojean.feature.player.impl.domain.di

import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.impl.domain.detail.DefaultPlayerDetailInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PlayerDomainModule {

    @Binds
    abstract fun bindPlayerDetailInteractor(
        interactor: DefaultPlayerDetailInteractor
    ): PlayerDetailInteractor
}