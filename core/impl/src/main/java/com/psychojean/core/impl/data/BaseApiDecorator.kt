package com.psychojean.core.impl.data

import com.psychojean.core.api.exception.ServerExceptionMapper

abstract class BaseApiDecorator(private val serverExceptionMapper: ServerExceptionMapper) {

    protected suspend fun <T> baseRequest(request: suspend () -> T): T {
        try {
            return request()
        } catch (exception: Exception) {
            throw serverExceptionMapper.map(exception)
        }
    }
}
