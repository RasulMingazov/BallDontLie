package com.psychojean.core.impl.data.database

import android.content.Context
import com.psychojean.core.impl.data.database.team.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class BallDatabaseModule {

    @Provides
    @Singleton
    fun provideBallDatabase(@ApplicationContext context: Context): BallDatabase =
        BallDatabase.Ball(context)

    @Provides
    @Singleton
    fun provideTeamDao(database: BallDatabase): TeamDao = database.teamDao()

}