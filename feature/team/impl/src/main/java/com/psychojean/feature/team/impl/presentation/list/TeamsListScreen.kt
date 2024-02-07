package com.psychojean.feature.team.impl.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.psychojean.core.impl.presentation.effect.EventEffect
import com.psychojean.core.impl.presentation.refresh.RefreshContainer
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.core.impl.presentation.ui.topbar.BallTopBar
import com.psychojean.feature.team.impl.R
import com.psychojean.feature.team.impl.presentation.model.TeamModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TeamsListScreen(
    modifier: Modifier,
    viewModel: TeamsListViewModel = hiltViewModel(),
    onStarredClick: () -> Unit = {}
) {

    val teamsState by viewModel.state.collectAsState()

    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) viewModel.refresh()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    EventEffect(flow = viewModel.event) { playerDetailEvent ->
        when (playerDetailEvent) {
            is TeamsListEvent.Refresh -> pullToRefreshState.startRefresh()
            is TeamsListEvent.EndRefresh -> pullToRefreshState.endRefresh()
        }
    }

    Scaffold(modifier = modifier,
        topBar = { BallTopBar(title = stringResource(id = R.string.teams)) },
        content = { padding ->
            RefreshContainer(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                pullToRefreshState = pullToRefreshState,
                isRefreshAvailable = teamsState.isRefreshAvailable
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
                        }
                    }

                    if (teamsState.teams != null) {
                        item { StarredTeamsCell(onStarredClick = onStarredClick) }
                        items(
                            items = teamsState.teams!!,
                            key = { team -> team.id }
                        ) { team ->
                            TeamCell(team = team, onStarTeamClick = viewModel::star)
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun StarredTeamsCell(modifier: Modifier = Modifier, onStarredClick: () -> Unit = {}) {
    Row(
        modifier = modifier
            .clickable { onStarredClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = stringResource(id = R.string.starred),
            textAlign = TextAlign.Start
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.starred)
        )
    }
}

@Composable
private fun TeamCellHeader(
    modifier: Modifier = Modifier,
    team: TeamModel,
    onStarTeamClick: (id: Int, isStarred: Boolean) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }
    Row(modifier = modifier.padding(vertical = 4.dp)) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = team.name,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
        )
        Box(contentAlignment = Alignment.TopEnd) {
            Icon(
                modifier = Modifier.clickable { showMenu = true },
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(id = R.string.more)
            )
            if (showMenu) {
                DropdownMenu(
                    onDismissRequest = { showMenu = false },
                    expanded = showMenu,
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(id = if (team.isStarred) R.string.remove else R.string.star)) },
                        onClick = {
                            showMenu = false
                            onStarTeamClick(team.id, !team.isStarred)
                        })
                }
            }
        }
    }
}

@Composable
internal fun TeamCell(
    modifier: Modifier = Modifier,
    team: TeamModel,
    onStarTeamClick: (id: Int, isStarred: Boolean) -> Unit = { _, _ -> }
) {
    Card(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Box(contentAlignment = Alignment.TopStart) {
            Column(modifier = Modifier.padding(16.dp)) {
                TeamCellHeader(team = team, onStarTeamClick = onStarTeamClick)
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
            "Phoenix",
            false
        )
    )
}
