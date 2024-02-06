package com.psychojean.core.impl.presentation.error

import com.psychojean.core.api.error.ErrorType
import com.psychojean.core.api.error.ErrorTypeMapper
import com.psychojean.core.api.exception.ServerUnavailableException
import java.net.UnknownHostException

object DefaultErrorTypeMapper : ErrorTypeMapper {

    override fun map(item: Throwable): ErrorType = when (item) {
        is UnknownHostException, is ServerUnavailableException -> NoConnectionErrorType
        else -> GenericErrorType
    }
}