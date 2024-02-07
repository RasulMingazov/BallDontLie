package com.psychojean.feature.team.impl.data.local.di

import com.psychojean.core.impl.data.database.team.TeamDao
import com.psychojean.feature.team.api.data.local.TeamLocalDataSource
import com.psychojean.feature.team.impl.data.local.DefaultTeamLocalDataSource
import com.psychojean.feature.team.impl.data.local.mapper.DefaultTeamDataToLocalMapper
import com.psychojean.feature.team.impl.data.local.mapper.DefaultTeamLocalToDataMapper
import com.psychojean.feature.team.impl.data.local.mapper.TeamDataToLocalMapper
import com.psychojean.feature.team.impl.data.local.mapper.TeamLocalToDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class TeamLocalModule {

    @Provides
    fun provideTeamLocalToDataMapper(): TeamLocalToDataMapper = DefaultTeamLocalToDataMapper

    @Provides
    fun provideTeamDataToLocalMapper(): TeamDataToLocalMapper = DefaultTeamDataToLocalMapper

    @Provides
    fun provideTeamLocalDataSource(
        teamDao: TeamDao,
        teamLocalToDataMapper: TeamLocalToDataMapper,
        teamDataToLocalMapper: TeamDataToLocalMapper
    ): TeamLocalDataSource =
        DefaultTeamLocalDataSource(teamDao, teamLocalToDataMapper, teamDataToLocalMapper)
}