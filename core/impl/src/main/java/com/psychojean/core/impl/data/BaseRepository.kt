package com.psychojean.core.impl.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.psychojean.core.impl.data.paging.BasePagingDataSource
import kotlinx.coroutines.flow.Flow

abstract class BaseRepository {

    protected fun <T : Any> doPagingRequest(
        pagingSource: BasePagingDataSource<T>,
        pageSize: Int = 10,
        enablePlaceholders: Boolean = true,
    ): Flow<PagingData<T>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = enablePlaceholders
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}