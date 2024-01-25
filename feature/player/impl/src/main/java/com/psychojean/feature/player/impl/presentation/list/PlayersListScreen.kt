package com.psychojean.feature.player.impl.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@Composable
fun PlayersListScreen(
    modifier: Modifier,
    viewModel: PlayersListViewModel = hiltViewModel<PlayersListViewModel>(),
    onDetailClick: (id: Int) -> Unit
) {
    Text(text = "List", Modifier.clickable {
        onDetailClick(133)
    })
}