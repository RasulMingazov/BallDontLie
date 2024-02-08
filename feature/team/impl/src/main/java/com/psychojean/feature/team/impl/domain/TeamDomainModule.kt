package com.psychojean.feature.team.impl.domain

import com.psychojean.feature.team.impl.domain.list.DefaultTeamsListInteractor
import com.psychojean.feature.team.impl.domain.list.TeamsListInteractor
import com.psychojean.feature.team.impl.domain.starred.DefaultStarredTeamsListInteractor
import com.psychojean.feature.team.impl.domain.starred.StarredTeamsListInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface TeamDomainModule {

    @Binds
    fun bindTeamsListInteractor(
        interactor: DefaultTeamsListInteractor
    ): TeamsListInteractor

    @Binds
    fun bindStarredTeamsListInteractor(
        interactor: DefaultStarredTeamsListInteractor
    ): StarredTeamsListInteractor
}