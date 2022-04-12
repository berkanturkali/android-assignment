package com.arabam.android.assignment.domain.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.arabam.android.assignment.remote.ApiService
import com.arabam.android.assignment.remote.mapper.AdvertRemoteMapper
import com.arabam.android.assignment.remote.mapper.DetailAdvertMapper
import com.arabam.android.assignment.remote.model.DetailAdvert
import com.arabam.android.assignment.remote.model.ListingAdvert
import com.arabam.android.assignment.remote.model.Resource
import com.arabam.android.assignment.remote.pagination.AdvertsPagingSource
import com.arabam.android.assignment.domain.data.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class AdvertRepoImpl @Inject constructor(
    private val api: ApiService,
    private val advertMapper: AdvertRemoteMapper,
    private val detailAdvertMapper: DetailAdvertMapper,
) : AdvertRepo {

    override fun allAdverts(
        categoryId: Int?,
        sort: Int?,
        direction: Int?,
        minYear: Int?,
        maxYear: Int?,
    ): Flow<PagingData<ListingAdvert>> =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false, maxSize = 100),
            pagingSourceFactory = {
                AdvertsPagingSource(
                    api,
                    categoryId,
                    sort,
                    direction,
                    minYear,
                    maxYear
                )
            }
        ).flow.map {
            it.map { dto ->
                advertMapper.mapFromModel(dto)
            }
        }

    override suspend fun advert(id: Int): Resource<DetailAdvert> =
        safeApiCall(detailAdvertMapper) {
            api.advert(id)
        }
}
