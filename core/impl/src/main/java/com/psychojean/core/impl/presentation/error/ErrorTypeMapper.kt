package com.psychojean.core.impl.presentation.error

import com.psychojean.core.api.Mapper
import com.psychojean.core.api.exception.ConnectionException
import java.net.UnknownHostException

interface ErrorTypeMapper : Mapper<Throwable, ErrorType> {

    class Default : ErrorTypeMapper {

        override fun map(item: Throwable): ErrorType = when (item) {
            is UnknownHostException -> ErrorType.NoConnection
            is ConnectionException -> ErrorType.NoConnection
            else -> ErrorType.Generic
        }
    }
}
