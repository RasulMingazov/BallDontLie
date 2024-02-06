package com.psychojean.core.impl.presentation.error.di

import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.impl.presentation.error.DefaultErrorTypeMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ErrorTypeModule {

    @Provides
    @ErrorQualifier
    fun provideErrorTypeMapper(): ErrorTypeMapper = DefaultErrorTypeMapper


}