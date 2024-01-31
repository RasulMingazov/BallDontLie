package com.psychojean.core.impl.presentation.paging

interface PagingStateListener {

    fun loadingError(throwable: Throwable) = Unit

    fun loadingStarted() = Unit

    fun loadingFinished() = Unit

    fun appendLoadingError(throwable: Throwable) = Unit

    fun appendLoadingStarted() = Unit

    fun appendLoadingFinished() = Unit

}