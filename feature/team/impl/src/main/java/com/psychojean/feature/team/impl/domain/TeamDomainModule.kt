package com.psychojean.feature.team.impl.domain

import com.psychojean.feature.team.api.domain.list.TeamsListInteractor
import com.psychojean.feature.team.impl.domain.list.DefaultTeamsListInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class TeamDomainModule {

    @Binds
    abstract fun bindTeamsListInteractor(
        interactor: DefaultTeamsListInteractor
    ): TeamsListInteractor
}