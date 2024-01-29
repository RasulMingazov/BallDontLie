package com.psychojean.feature.player.impl.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey

@Composable
internal fun PlayersListScreen(
    modifier: Modifier,
    viewModel: PlayersListViewModel = hiltViewModel<PlayersListViewModel>(),
    onDetailClick: (id: Int) -> Unit
) {
    val players = viewModel.players.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(count = players.itemCount, key = players.itemKey { it.id }) { index ->
            val player = players[index]
            Text(modifier = Modifier.clickable { onDetailClick(player?.id ?: -1) }, text = player?.fullName ?: "")
        }
    }
}