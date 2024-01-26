package com.psychojean.core.impl.data.exception

import com.psychojean.core.api.exception.ServerExceptionMapper
import com.psychojean.core.api.exception.ServerUnavailableException
import retrofit2.HttpException

class DefaultServerExceptionMapper : ServerExceptionMapper {

    override fun map(item: Throwable): Throwable = when (item) {
        is HttpException -> when {
            item.code() in 500..599 || item.code() == 404 -> ServerUnavailableException()
            else -> item
        }
        else -> item
    }
}