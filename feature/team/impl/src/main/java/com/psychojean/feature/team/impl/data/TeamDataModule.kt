package com.psychojean.feature.team.impl.data

import com.psychojean.feature.team.api.data.model.TeamDataToEntityMapper
import com.psychojean.feature.team.api.domain.TeamRepository
import com.psychojean.feature.team.impl.data.mapper.DefaultTeamDataToEntityMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface TeamDataModule {

    @Binds
    fun bindTeamDataToEntityMapper(teamDataToEntityMapper: DefaultTeamDataToEntityMapper): TeamDataToEntityMapper

    @Binds
    fun bindTeamRepository(teamRepository: DefaultTeamRepository): TeamRepository

}