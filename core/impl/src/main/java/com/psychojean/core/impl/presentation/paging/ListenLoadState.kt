package com.psychojean.core.impl.presentation.paging

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun listenPagingLoadState(
    loadState: CombinedLoadStates,
    pagingStateListener: PagingStateListener,
) {
    if (loadState.append is LoadState.Loading) pagingStateListener.appendLoadingStarted()
    if (loadState.append is LoadState.Error) pagingStateListener.appendLoadingError((loadState.append as LoadState.Error).error)
    if (loadState.append is LoadState.NotLoading) pagingStateListener.appendLoadingFinished()

    if (loadState.refresh is LoadState.Loading) pagingStateListener.loadingStarted()
    if (loadState.refresh is LoadState.Error) pagingStateListener.loadingError((loadState.refresh as LoadState.Error).error)
    if (loadState.refresh is LoadState.NotLoading) pagingStateListener.loadingFinished()
}