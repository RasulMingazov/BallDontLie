package com.psychojean.core.api.exception

import com.psychojean.core.api.Mapper

interface ServerExceptionMapper : Mapper<Throwable, Throwable>