package com.psychojean.feature.team.impl.data.local

import com.psychojean.feature.team.api.data.local.TeamLocalDataSource
import com.psychojean.feature.team.impl.data.local.mapper.DefaultTeamDataToLocalMapper
import com.psychojean.feature.team.impl.data.local.mapper.DefaultTeamLocalToDataMapper
import com.psychojean.feature.team.impl.data.local.mapper.TeamDataToLocalMapper
import com.psychojean.feature.team.impl.data.local.mapper.TeamLocalToDataMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface TeamLocalModule {

    @Binds
    fun bindTeamLocalToDataMapper(teamLocalToDataMapper: DefaultTeamLocalToDataMapper): TeamLocalToDataMapper

    @Binds
    fun bindTeamDataToLocalMapper(teamDataToLocalMapper: DefaultTeamDataToLocalMapper): TeamDataToLocalMapper

    @Binds
    fun bindTeamLocalDataSource(teamLocalDataSource: DefaultTeamLocalDataSource): TeamLocalDataSource
}