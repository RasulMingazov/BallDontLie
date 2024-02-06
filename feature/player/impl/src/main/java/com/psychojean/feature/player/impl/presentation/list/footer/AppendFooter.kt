package com.psychojean.feature.player.impl.presentation.list.footer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import com.psychojean.core.impl.presentation.paging.listenPagingAppend
import com.psychojean.core.impl.presentation.ui.footer.BallErrorFooter
import com.psychojean.core.impl.presentation.ui.footer.BallProgressFooter

@Composable
internal fun AppendFooter(
    modifier: Modifier = Modifier,
    viewModel: AppendFooterViewModel = hiltViewModel(),
    loadState: CombinedLoadStates
) {
    listenPagingAppend(loadState, viewModel)

    val state by viewModel.state.collectAsState()
    when {
        state.isLoading -> BallProgressFooter(modifier = modifier)
        state.error != null -> BallErrorFooter(modifier = modifier, errorType = state.error!!)
    }
}
