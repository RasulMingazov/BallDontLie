package com.psychojean.feature.team.impl.presentation.di

import com.psychojean.feature.team.impl.presentation.model.mapper.DefaultTeamEntityToModelMapper
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class TeamPresentationModule {

    @Provides
    fun provideTeamEntityToModelMapper(): TeamEntityToModelMapper =
        DefaultTeamEntityToModelMapper

}