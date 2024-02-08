package com.psychojean.feature.player.impl.data.remote

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.impl.data.exception.di.ServerExceptionQualifier
import com.psychojean.feature.player.api.data.remote.PlayerRemoteDataSource
import com.psychojean.feature.player.impl.data.remote.service.PlayerService
import com.psychojean.feature.player.impl.data.remote.service.PlayerServiceDecorator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
internal interface PlayerRemoteModule {

    @Binds
    fun bindPlayerRemoteToDataMapper(
        playerRemoteToDataMapper: DefaultPlayerRemoteToDataMapper
    ): PlayerRemoteToDataMapper

    @Binds
    fun bindPlayerRemoteDataSource(
        playerRemoteDataSource: DefaultPlayerRemoteDataSource
    ): PlayerRemoteDataSource

    companion object {

        @Provides
        @Named("service")
        fun providePlayerService(@Named("api") retrofit: Retrofit): PlayerService =
            retrofit.create(PlayerService::class.java)

        @Provides
        fun providePlayerServiceDecorated(
            @Named("service") service: PlayerService,
            @ServerExceptionQualifier serverExceptionMapper: ServerExceptionMapper
        ): PlayerService = PlayerServiceDecorator(service, serverExceptionMapper)
    }
}
