package com.psychojean.feature.team.impl.data.remote

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.impl.data.exception.di.ServerExceptionQualifier
import com.psychojean.feature.team.api.data.remote.TeamRemoteDataSource
import com.psychojean.feature.team.api.data.remote.TeamRemoteToDataMapper
import com.psychojean.feature.team.impl.data.remote.model.mapper.DefaultTeamRemoteToDataMapper
import com.psychojean.feature.team.impl.data.remote.service.TeamService
import com.psychojean.feature.team.impl.data.remote.service.TeamServiceDecorator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
internal interface TeamRemoteModule {

    @Binds
    fun bindTeamRemoteToDataMapper(teamRemoteToDataMapper: DefaultTeamRemoteToDataMapper): TeamRemoteToDataMapper

    @Binds
    fun bindTeamRemoteDataSource(teamRemoteDataSource: DefaultTeamRemoteDataSource): TeamRemoteDataSource

    companion object {

        @Provides
        @Named("service")
        fun provideTeamService(@Named("api") retrofit: Retrofit): TeamService =
            retrofit.create()

        @Provides
        fun provideTeamServiceDecorated(
            @Named("service") service: TeamService,
            @ServerExceptionQualifier serverExceptionMapper: ServerExceptionMapper
        ): TeamService = TeamServiceDecorator(service, serverExceptionMapper)
    }
}
