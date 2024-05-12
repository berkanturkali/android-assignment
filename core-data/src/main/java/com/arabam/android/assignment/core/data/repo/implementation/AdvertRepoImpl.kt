package com.arabam.android.assignment.core.data.repo.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.arabam.android.assignment.core.data.repo.abstraction.AdvertRepo
import com.arabam.android.assignment.core.data.util.safeApiCall
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.model.Resource
import com.arabam.android.assignment.core.network.ApiService
import com.arabam.android.assignment.core.network.mapper.AdvertRemoteMapper
import com.arabam.android.assignment.core.network.mapper.DetailAdvertMapper
import com.arabam.android.assignment.core.network.pagination.AdvertsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdvertRepoImpl @Inject constructor(
    private val api: ApiService,
    private val advertMapper: AdvertRemoteMapper,
    private val detailAdvertMapper: DetailAdvertMapper,
) : AdvertRepo {

    override fun fetchAllAdverts(
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

    override suspend fun fetchAdvert(id: Int): Resource<DetailAdvert> =
        safeApiCall(detailAdvertMapper) {
            api.fetchAdvert(id)
        }
}
