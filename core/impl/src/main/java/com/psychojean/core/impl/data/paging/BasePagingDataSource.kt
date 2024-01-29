package com.psychojean.core.impl.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingDataSource<T : Any> : PagingSource<Int, T>() {

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    abstract suspend fun pagingApiRequest(offset: Int, loadSize: Int): Result<List<T>>

    override fun getRefreshKey(state: PagingState<Int, T>): Int? =
        state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageIndex = params.key ?: 1

        val offset = if (params.key != null) ((pageIndex - 1) * NETWORK_PAGE_SIZE) else 0

        val result = pagingApiRequest(offset, params.loadSize)

        if (result.isSuccess) {
            val requireList = result.getOrNull()!!

            val nextKey = if (requireList.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            return LoadResult.Page(
                data = requireList,
                prevKey = null,
                nextKey = nextKey
            )
        } else {
            return LoadResult.Error(result.exceptionOrNull()!!)
        }
    }
}