package com.psychojean.feature.player.impl.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
internal fun PlayersListScreen(
    modifier: Modifier,
    viewModel: PlayersListViewModel = hiltViewModel<PlayersListViewModel>(),
    onDetailClick: (id: Int) -> Unit
) {
    val players = viewModel.players.collectAsLazyPagingItems()

    Text(text = "Test", Modifier.padding(top = 44.dp).clickable {
        onDetailClick(133)
    })

//    LazyColumn(modifier = modifier.fillMaxSize()) {
//        items(count = players.itemCount, key = players.itemKey { it.id }) { index ->
//            val player = players[index]
//            Text(modifier = Modifier.clickable { onDetailClick(player?.id ?: -1) }, text = player?.fullName ?: "")
//        }
//    }
}