package com.psychojean.core.api

interface Mapper<in T, out P> {

    fun map(item: T): P
}