package com.psychojean.feature.team.impl.presentation.starred

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.psychojean.core.api.error.ErrorType
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.topbar.BallTopBar
import com.psychojean.feature.team.impl.R
import com.psychojean.feature.team.impl.presentation.list.EmptyList
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
            StarredTeamsContent(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                teamsState = teamsState,
                onErrorClick = viewModel::retry,
                onStarTeamClick = viewModel::removeFromStarred
            )
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StarredTeamsContent(
    modifier: Modifier = Modifier,
    teamsState: StarredTeamsListUiState,
    onErrorClick: (errorType: ErrorType) -> Unit,
    onStarTeamClick: (id: Int) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        when {
            teamsState.isLoading -> item { BallProgressStub(Modifier.fillParentMaxSize()) }
            teamsState.isTeamsEmpty -> item { EmptyList(Modifier.fillParentMaxSize()) }
            teamsState.error != null -> item {
                BallErrorStub(
                    modifier = Modifier.fillParentMaxSize(),
                    errorType = teamsState.error,
                    isButtonLoading = teamsState.isRetry,
                    onButtonClick = onErrorClick
                )
            }

            teamsState.teams != null -> {
                items(items = teamsState.teams, key = { team -> team.id }) { team ->
                    TeamCell(modifier = Modifier.animateItemPlacement(), team = team, onStarTeamClick = { id, _ -> onStarTeamClick(id) })
                }
            }
        }

    }
}
