package com.psychojean.core.impl.presentation.paging

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun listenPagingLoadState(
    loadState: CombinedLoadStates,
    pagingStateListener: PagingStateListener,
) {

    when (loadState.append) {
        is LoadState.Loading -> pagingStateListener.appendLoadingStarted()
        is LoadState.NotLoading -> pagingStateListener.appendLoadingFinished()
        is LoadState.Error -> pagingStateListener.appendLoadingError((loadState.append as LoadState.Error).error)
    }

    when (loadState.refresh) {
        is LoadState.Loading -> pagingStateListener.loadingStarted()
        is LoadState.NotLoading -> pagingStateListener.loadingFinished()
        is LoadState.Error -> pagingStateListener.loadingError((loadState.refresh as LoadState.Error).error)
    }
}
