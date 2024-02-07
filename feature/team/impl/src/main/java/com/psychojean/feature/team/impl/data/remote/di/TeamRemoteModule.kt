package com.psychojean.feature.team.impl.data.remote.di

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.impl.data.exception.di.ServerExceptionQualifier
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.data.remote.TeamRemoteToDataMapper
import com.psychojean.feature.team.impl.data.remote.DefaultTeamRemoteDataSource
import com.psychojean.feature.team.impl.data.remote.mapper.DefaultTeamRemoteToDataMapper
import com.psychojean.feature.team.impl.data.remote.service.TeamService
import com.psychojean.feature.team.impl.data.remote.service.TeamServiceDecorator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
internal class TeamRemoteModule {

    @Provides
    @Named("service")
    fun provideTeamService(@Named("api") retrofit: Retrofit): TeamService =
        retrofit.create(TeamService::class.java)

    @Provides
    fun provideTeamServiceDecorated(
        @Named("service") service: TeamService,
        @ServerExceptionQualifier serverExceptionMapper: ServerExceptionMapper
    ): TeamService = TeamServiceDecorator(service, serverExceptionMapper)

    @Provides
    fun provideTeamRemoteToDataMapper(): TeamRemoteToDataMapper = DefaultTeamRemoteToDataMapper

    @Provides
    fun provideTeamRemoteDataSource(
        service: TeamService,
        teamRemoteToDataMapper: TeamRemoteToDataMapper
    ): TeamRemoteDataSource =
        DefaultTeamRemoteDataSource(service, teamRemoteToDataMapper)
}