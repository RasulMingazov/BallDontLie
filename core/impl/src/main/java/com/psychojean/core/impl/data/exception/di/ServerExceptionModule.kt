package com.psychojean.core.impl.data.exception.di

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.impl.data.exception.DefaultServerExceptionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServerExceptionModule {

    @Provides
    @ServerExceptionQualifier
    fun provideServerExceptionMapper(): ServerExceptionMapper = DefaultServerExceptionMapper()
}