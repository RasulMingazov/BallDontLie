package com.psychojean.core.api

interface ResultToUi<U, R> {

    fun U.copyFromResult(result: R): U
}