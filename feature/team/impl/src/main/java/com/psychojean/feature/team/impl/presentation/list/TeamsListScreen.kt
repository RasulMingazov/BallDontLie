package com.psychojean.feature.team.impl.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.psychojean.core.impl.presentation.effect.EventEffect
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.core.impl.presentation.refresh.RefreshContainer
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.core.impl.presentation.ui.topbar.BallTopBar
import com.psychojean.feature.team.impl.R
import com.psychojean.feature.team.impl.presentation.model.TeamModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TeamsListScreen(modifier: Modifier, viewModel: TeamsListViewModel = hiltViewModel()) {
    val teamsState = viewModel.state.collectAsState()

    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) viewModel.refresh()

    EventEffect(flow = viewModel.event) { playerDetailEvent ->
        when (playerDetailEvent) {
            is TeamsListEvent.Refresh -> pullToRefreshState.startRefresh()
            is TeamsListEvent.EndRefresh -> pullToRefreshState.endRefresh()
        }
    }

    Scaffold(modifier = modifier,
        topBar = { BallTopBar(title = stringResource(id = R.string.teams)) },
        content = { padding ->
            TeamsListScreenContent(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                teamsState = teamsState.value,
                pullToRefreshState = pullToRefreshState,
                onRetryClick = { viewModel.retry() }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TeamsListScreenContent(
    modifier: Modifier = Modifier,
    teamsState: TeamsListState,
    pullToRefreshState: PullToRefreshState,
    onRetryClick: (errorType: ErrorType) -> Unit = {}
) {
    RefreshContainer(
        modifier = modifier,
        pullToRefreshState = pullToRefreshState
    ) {
        LazyColumn(modifier = Modifier) {
            when (teamsState) {
                is TeamsListState.Loading -> item { BallProgressStub(Modifier.fillParentMaxSize()) }
                is TeamsListState.Error -> item {
                    BallErrorStub(
                        modifier = Modifier.fillParentMaxSize(),
                        errorType = teamsState.errorType,
                        onButtonClick = onRetryClick
                    )
                }

                is TeamsListState.Success -> items(
                    items = teamsState.teams
                ) { TeamCell(team = it) }
            }
        }
    }
}

@Composable
private fun TeamCell(
    modifier: Modifier = Modifier,
    team: TeamModel
) {

    Card(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            PairText(
                modifier = Modifier.padding(vertical = 4.dp),
                firstText = team.name,
                secondText = team.abbreviation,
                firstTextStyle = MaterialTheme.typography.titleMedium,
                secondTextStyle = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            PairText(
                modifier = Modifier.padding(vertical = 4.dp),
                firstText = stringResource(id = R.string.conference),
                secondText = team.conference,
                firstTextStyle = MaterialTheme.typography.titleSmall,
                secondTextStyle = MaterialTheme.typography.bodySmall
            )
            PairText(
                modifier = Modifier.padding(vertical = 4.dp),
                firstText = stringResource(id = R.string.division),
                secondText = team.division,
                firstTextStyle = MaterialTheme.typography.titleSmall,
                secondTextStyle = MaterialTheme.typography.bodySmall
            )
            PairText(
                modifier = Modifier.padding(vertical = 4.dp),
                firstText = stringResource(id = R.string.city),
                secondText = team.city,
                firstTextStyle = MaterialTheme.typography.titleSmall,
                secondTextStyle = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
@Preview
private fun TeamCellsPreview() {
    Column {
        for (i in 0..100) {
            TeamCellPreview()
        }
    }
}

@Composable
@Preview
private fun TeamCellPreview() {
    TeamCell(
        team = TeamModel(
            0,
            "Phoenix Suns",
            "Pacific",
            "PHX",
            "West",
            "Phoenix"
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun SuccessScreenPreview() {
    val teamsList = mutableListOf<TeamModel>()
    for (i in 0..100) {
        teamsList.add(
            TeamModel(
                i,
                "Phoenix Suns",
                "Pacific",
                "PHX",
                "West",
                "Phoenix"
            )
        )
    }
    TeamsListScreenContent(
        teamsState = TeamsListState.Success(teamsList),
        pullToRefreshState = rememberPullToRefreshState()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ErrorScreenPreview() {
    TeamsListScreenContent(
        teamsState = TeamsListState.Error(ErrorType.Generic),
        pullToRefreshState = rememberPullToRefreshState()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun ProgressScreenPreview() {
    TeamsListScreenContent(
        teamsState = TeamsListState.Loading,
        pullToRefreshState = rememberPullToRefreshState()
    )
}