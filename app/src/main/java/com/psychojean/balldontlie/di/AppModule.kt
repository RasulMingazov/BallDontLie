package com.psychojean.balldontlie.di

import com.psychojean.core.api.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDispatcher(): Dispatcher = Dispatcher.Default()
}