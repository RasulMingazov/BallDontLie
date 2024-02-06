package com.psychojean.feature.player.impl.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.psychojean.core.impl.presentation.effect.EventEffect
import com.psychojean.core.impl.presentation.paging.listenPagingState
import com.psychojean.core.impl.presentation.refresh.RefreshContainer
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.core.impl.presentation.ui.topbar.BallTopBar
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel
import com.psychojean.feature.player.impl.presentation.list.footer.AppendFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PlayersListScreen(
    modifier: Modifier,
    viewModel: PlayersListViewModel = hiltViewModel(),
    onDetailClick: (id: Int) -> Unit
) {
    val playersState by viewModel.state.collectAsState()
    val playersPagingItems = viewModel.players.collectAsLazyPagingItems()

    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) viewModel.refresh()

    EventEffect(flow = viewModel.event) { playerDetailEvent ->
        when (playerDetailEvent) {
            is PlayersListEvent.Retry -> playersPagingItems.retry()
            is PlayersListEvent.EndRefresh -> pullToRefreshState.endRefresh()
            is PlayersListEvent.Refresh -> {
                pullToRefreshState.startRefresh()
                playersPagingItems.refresh()
            }
        }
    }

    Scaffold(modifier = modifier,
        topBar = { BallTopBar(title = stringResource(id = R.string.players)) },
        content = { padding ->
            RefreshContainer(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                pullToRefreshState = pullToRefreshState
            ) {
                LazyColumn {
                    listenPagingState(playersPagingItems.loadState, viewModel)

                    item {
                        when {
                            playersState.isLoading -> BallProgressStub(Modifier.fillParentMaxSize())
                            playersState.error != null -> BallErrorStub(
                                modifier = Modifier.fillParentMaxSize(),
                                errorType = playersState.error!!,
                                onButtonClick = { viewModel.retry() }
                            )
                        }
                    }

                    items(
                        count = playersPagingItems.itemCount,
                        key = playersPagingItems.itemKey { player -> player.id }) { index ->
                        playersPagingItems[index]?.let { player ->
                            PlayerCell(player = player, onDetailClick = onDetailClick)
                            HorizontalDivider(thickness = 0.25.dp)
                        }
                    }

                    item { AppendFooter(loadState = playersPagingItems.loadState) }
                }
            }
        }
    )
}

@Composable
private fun PlayerCell(
    modifier: Modifier = Modifier,
    player: PlayerModel,
    onDetailClick: (id: Int) -> Unit = {}
) {
    PairText(
        modifier = modifier
            .clickable { onDetailClick(player.id) }
            .padding(vertical = 20.dp, horizontal = 24.dp)
            .fillMaxWidth(),
        firstText = player.fullName,
        secondText = player.position
    )
}

@Composable
@Preview
private fun PlayerCellsPreview() {
    Column {
        for (i in 0..100) {
            PlayerCellPreview()
        }
    }
}

@Composable
@Preview
private fun PlayerCellPreview() {
    PlayerCell(
        player = PlayerModel(
            0,
            "Tyler Dorsey",
            "6 feet, 182.8 cm",
            "183 pounds, 83.0 kg",
            "G",
            0,
            "Chicago"
        )
    )
}

