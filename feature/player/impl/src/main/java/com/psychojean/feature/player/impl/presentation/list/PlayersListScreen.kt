package com.psychojean.feature.player.impl.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.psychojean.core.impl.presentation.effect.EventEffect
import com.psychojean.core.impl.presentation.error.ErrorType
import com.psychojean.core.impl.presentation.paging.PagingStateListener
import com.psychojean.core.impl.presentation.paging.listenPagingLoadState
import com.psychojean.core.impl.presentation.ui.footer.BallErrorFooter
import com.psychojean.core.impl.presentation.ui.footer.BallProgressFooter
import com.psychojean.core.impl.presentation.ui.stub.BallErrorStub
import com.psychojean.core.impl.presentation.ui.stub.BallProgressStub
import com.psychojean.core.impl.presentation.ui.text.PairText
import com.psychojean.core.impl.presentation.ui.topbar.BallTopBar
import com.psychojean.feature.player.impl.R
import com.psychojean.feature.player.impl.presentation.detail.model.PlayerModel
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun PlayersListScreen(
    modifier: Modifier,
    viewModel: PlayersListViewModel = hiltViewModel<PlayersListViewModel>(),
    onDetailClick: (id: Int) -> Unit
) {
    val playersState = viewModel.state.collectAsState()
    val playersAppendState = viewModel.appendState.collectAsState()
    val playersPagingItems = viewModel.players.collectAsLazyPagingItems()

    EventEffect(flow = viewModel.event) { playerDetailEvent ->
        when (playerDetailEvent) {
            is PlayersListEvent.Retry -> playersPagingItems.retry()
        }
    }

    Scaffold(modifier = modifier,
        topBar = { BallTopBar(title = stringResource(id = R.string.players)) },
        content = { padding ->
            PlayersListScreenContent(
                modifier = Modifier.padding(top = padding.calculateTopPadding()),
                playersListState = playersState.value,
                playersAppendState = playersAppendState.value,
                playersPagingItems = playersPagingItems,
                pagingStateListener = viewModel,
                onDetailClick = onDetailClick,
                onRetryClick = { viewModel.retry() }
            )
        }
    )
}

@Composable
private fun PlayersListScreenContent(
    modifier: Modifier = Modifier,
    playersListState: PlayersListState,
    playersAppendState: PlayersListAppendState,
    playersPagingItems: LazyPagingItems<PlayerModel>,
    pagingStateListener: PagingStateListener,
    onDetailClick: (id: Int) -> Unit = {},
    onRetryClick: (errorType: ErrorType) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        listenPagingLoadState(playersPagingItems.loadState, pagingStateListener)

        item {
            when (playersListState) {
                is PlayersListState.NotLoading -> Unit
                is PlayersListState.Loading -> BallProgressStub(Modifier.fillParentMaxSize())
                is PlayersListState.Error ->
                    BallErrorStub(
                        modifier = Modifier.fillParentMaxSize(),
                        errorType = playersListState.errorType,
                        onButtonClick = onRetryClick
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

        item {
            when (playersAppendState) {
                is PlayersListAppendState.Loading -> BallProgressFooter()
                is PlayersListAppendState.Error -> BallErrorFooter(errorType = playersAppendState.errorType)
                is PlayersListAppendState.NotLoading -> Unit
            }
        }
    }
}


@Composable
private fun PlayerCell(
    modifier: Modifier = Modifier,
    player: PlayerModel,
    onDetailClick: (id: Int) -> Unit = {}
) {
    PairText(
        modifier = modifier
            .padding(vertical = 20.dp, horizontal = 24.dp)
            .fillMaxWidth()
            .clickable { onDetailClick(player.id) },
        firstText = player.fullName,
        secondText = player.position
    )
}

@Composable
@Preview
private fun SuccessPreview() {
    val playersList = mutableListOf<PlayerModel>()
    for (i in 0..100) {
        playersList.add(
            PlayerModel(
                i,
                "Tyler Dorsey",
                "6 feet, 182.8 cm",
                "183 pounds, 83.0 kg",
                "G",
                0,
                "Chicago"
            )
        )
    }
    PlayersListScreenContent(
        playersListState = PlayersListState.NotLoading,
        playersAppendState = PlayersListAppendState.NotLoading,
        playersPagingItems = flowOf(PagingData.from(playersList)).collectAsLazyPagingItems(),
        pagingStateListener = object : PagingStateListener {}
    )
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

@Composable
@Preview
private fun ErrorPreview() {
    PlayersListScreenContent(
        playersListState = PlayersListState.Error(ErrorType.Generic),
        playersAppendState = PlayersListAppendState.NotLoading,
        playersPagingItems = flowOf(PagingData.from(listOf<PlayerModel>())).collectAsLazyPagingItems(),
        pagingStateListener = object : PagingStateListener {}
    )
}

@Composable
@Preview
private fun ProgressPreview() {
    PlayersListScreenContent(
        playersListState = PlayersListState.Loading,
        playersAppendState = PlayersListAppendState.NotLoading,
        playersPagingItems = flowOf(PagingData.from(listOf<PlayerModel>())).collectAsLazyPagingItems(),
        pagingStateListener = object : PagingStateListener {}
    )
}
