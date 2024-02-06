package com.psychojean.core.impl.presentation.paging

interface PagingStateListener {

    fun loadingError(throwable: Throwable) = Unit

    fun loadingStarted() = Unit

    fun loadingFinished() = Unit

}

interface PagingAppendListener {

    fun appendLoadingError(throwable: Throwable) = Unit

    fun appendLoadingStarted() = Unit

    fun appendLoadingFinished() = Unit

}