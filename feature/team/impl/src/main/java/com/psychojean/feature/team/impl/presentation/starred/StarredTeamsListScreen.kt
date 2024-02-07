package com.psychojean.feature.team.impl.presentation.starred

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.topbar.BallTopBar
import com.psychojean.feature.team.impl.R
import com.psychojean.feature.team.impl.presentation.list.TeamCell

@Composable
internal fun StarredTeamsListScreen(
    modifier: Modifier,
    viewModel: StarredTeamsListViewModel = hiltViewModel()
) {
    val teamsState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Scaffold(modifier = modifier,
        topBar = { BallTopBar(title = stringResource(id = R.string.teams)) },
        content = { padding ->
            Box(
                modifier = Modifier.padding(top = padding.calculateTopPadding())
            ) {
                LazyColumn(modifier = Modifier) {
                    item {
                        when {
                            teamsState.isLoading -> BallProgressStub(Modifier.fillParentMaxSize())
                            teamsState.error != null -> BallErrorStub(
                                modifier = Modifier.fillParentMaxSize(),
                                errorType = teamsState.error!!,
                                isButtonLoading = teamsState.isRetry,
                                onButtonClick = viewModel::retry
                            )

                            teamsState.isTeamsEmpty -> EmptyList(Modifier.fillParentMaxSize())
                        }
                    }

                    if (teamsState.teams != null) {
                        items(
                            items = teamsState.teams!!,
                            key = { team -> team.id }
                        ) { team ->
                            TeamCell(
                                team = team,
                                onStarTeamClick = { teamId, _ -> viewModel.removeFromStarred(teamId) })
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun EmptyList(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.there_is_nothing_yet)
        )
    }
}