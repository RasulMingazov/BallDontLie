package com.psychojean.feature.player.impl.domain

import com.psychojean.feature.player.impl.domain.detail.DefaultPlayerDetailInteractor
import com.psychojean.feature.player.impl.domain.detail.PlayerDetailInteractor
import com.psychojean.feature.player.impl.domain.list.DefaultPlayersListInteractor
import com.psychojean.feature.player.impl.domain.list.PlayersListInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface PlayerDomainModule {

    @Binds
    fun bindPlayerDetailInteractor(
        interactor: DefaultPlayerDetailInteractor
    ): PlayerDetailInteractor

    @Binds
    fun bindPlayersListInteractor(
        interactor: DefaultPlayersListInteractor
    ): PlayersListInteractor
}