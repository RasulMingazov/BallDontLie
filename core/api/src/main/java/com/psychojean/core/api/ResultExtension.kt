package com.psychojean.core.api

fun <T> Result<T>.exceptionOrThrow(): Throwable {
    return exceptionOrNull() ?: throw Exception()
}