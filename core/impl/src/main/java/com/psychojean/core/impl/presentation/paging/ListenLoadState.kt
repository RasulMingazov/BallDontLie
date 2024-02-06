package com.psychojean.core.impl.presentation.paging

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun listenPagingState(
    loadState: CombinedLoadStates,
    pagingStateListener: PagingStateListener
) {

    when (loadState.refresh) {
        is LoadState.Loading -> pagingStateListener.loadingStarted()
        is LoadState.NotLoading -> pagingStateListener.loadingFinished()
        is LoadState.Error -> pagingStateListener.loadingError((loadState.refresh as LoadState.Error).error)
    }
}

fun listenPagingAppend(
    loadState: CombinedLoadStates,
    pagingAppendListener: PagingAppendListener
) {
    when (loadState.append) {
        is LoadState.Loading -> pagingAppendListener.appendLoadingStarted()
        is LoadState.NotLoading -> pagingAppendListener.appendLoadingFinished()
        is LoadState.Error -> pagingAppendListener.appendLoadingError((loadState.append as LoadState.Error).error)
    }
}
