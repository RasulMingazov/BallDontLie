package com.psychojean.core.impl.presentation.save.di

import androidx.lifecycle.SavedStateHandle
import com.psychojean.core.impl.presentation.save.SaveStateKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SaveStateModule {

    @Provides
    fun provideDigitSaveStateKey(saveStateHandle: SavedStateHandle): SaveStateKey<Int> = SaveStateKey.Digit(saveStateHandle)

}