package com.psychojean.core.impl.core.di

import android.content.Context
import com.psychojean.core.api.TextProvider
import com.psychojean.core.impl.core.DefaultTextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class TextProviderModule {

    @Provides
    fun provideTextProvider(@ApplicationContext context: Context): TextProvider =
        DefaultTextProvider(context)

}