package com.arabam.android.assigment.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.data.service.Api
import com.arabam.android.assigment.utils.resize
import retrofit2.HttpException
import java.io.IOException

class AdvertsPagingSource(
    private val service: Api,
    private val sort: Int?,
    private val direction: Int?,
    private val min: Int?,
    private val max: Int?,
) : PagingSource<Int, ListingAdvert>() {
    override fun getRefreshKey(state: PagingState<Int, ListingAdvert>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(10)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(10)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListingAdvert> {
        val skip = params.key ?: 0
        val take = 10
        return try {
            val response =
                service.allAdverts(skip, sort = sort, direction = direction, min = min, max = max).map {
                    it.photo = it.photo.resize()
                    it
                }
            val prevKey = if (skip == 0) {
                null
            } else {
                skip - take
            }
            val nextKey = if (response.isEmpty()) null else skip + take
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}