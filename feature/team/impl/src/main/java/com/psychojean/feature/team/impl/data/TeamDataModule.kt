package com.psychojean.feature.team.impl.data

import com.psychojean.feature.team.api.data.local.TeamLocalDataSource
import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.impl.data.mapper.DefaultTeamDataToEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class TeamDataModule {

    @Provides
    fun provideTeamDataToEntityMapper(): TeamDataToEntityMapper = DefaultTeamDataToEntityMapper

    @Provides
    fun provideTeamRepository(
        teamRemoteDataSource: TeamRemoteDataSource,
        teamLocalDataSource: TeamLocalDataSource,
        teamDataToEntityMapper: TeamDataToEntityMapper
    ): TeamRepository = DefaultTeamRepository(
        teamRemoteDataSource, teamLocalDataSource, teamDataToEntityMapper
    )
}