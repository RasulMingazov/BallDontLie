package com.psychojean.feature.player.impl.domain

import com.psychojean.feature.player.api.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.api.domain.list.PlayersListInteractor
import com.psychojean.feature.player.impl.domain.detail.DefaultPlayerDetailInteractor
import com.psychojean.feature.player.impl.domain.list.DefaultPlayersListInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class PlayerDomainModule {

    @Binds
    abstract fun bindPlayerDetailInteractor(
        interactor: DefaultPlayerDetailInteractor
    ): PlayerDetailInteractor

    @Binds
    abstract fun bindPlayersListInteractor(
        interactor: DefaultPlayersListInteractor
    ): PlayersListInteractor
}