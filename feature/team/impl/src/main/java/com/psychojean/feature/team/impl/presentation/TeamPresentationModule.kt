package com.psychojean.feature.team.impl.presentation

import com.psychojean.feature.team.impl.presentation.list.DefaultTeamsToUi
import com.psychojean.feature.team.impl.presentation.list.TeamsToUi
import com.psychojean.feature.team.impl.presentation.model.mapper.DefaultStarredTeamsMapper
import com.psychojean.feature.team.impl.presentation.model.mapper.DefaultTeamEntityToModelMapper
import com.psychojean.feature.team.impl.presentation.model.mapper.StarredTeamsMapper
import com.psychojean.feature.team.impl.presentation.model.mapper.TeamEntityToModelMapper
import com.psychojean.feature.team.impl.presentation.starred.DefaultStarredTeamsToUi
import com.psychojean.feature.team.impl.presentation.starred.StarredTeamsToUi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal interface TeamPresentationModule {

    @Binds
    fun bindTeamEntityToModelMapper(
        teamEntityToModelMapper: DefaultTeamEntityToModelMapper
    ): TeamEntityToModelMapper

    @Binds
    fun bindDefaultStarredTeamsMapper(
        starredTeamsMapper: DefaultStarredTeamsMapper
    ): StarredTeamsMapper

    @Binds
    fun bindStarredTeamsToUi(
        starredTeamsToUi: DefaultStarredTeamsToUi
    ): StarredTeamsToUi

    @Binds
    fun bindTeamsToUi(
        teamsToUi: DefaultTeamsToUi
    ): TeamsToUi
}
