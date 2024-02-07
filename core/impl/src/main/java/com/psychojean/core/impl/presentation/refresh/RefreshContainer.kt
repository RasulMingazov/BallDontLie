package com.psychojean.core.impl.presentation.refresh

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RefreshContainer(
    modifier: Modifier = Modifier,
    pullToRefreshState: PullToRefreshState,
    isRefreshAvailable: Boolean = true,
    content: @Composable () -> Unit
) {
    val isAvailable by remember(isRefreshAvailable) { mutableStateOf(isRefreshAvailable) }

    Box(
        modifier = modifier
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        content()
        if (isAvailable) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState
            )
        }
    }

}