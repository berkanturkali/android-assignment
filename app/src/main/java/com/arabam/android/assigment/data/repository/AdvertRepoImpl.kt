package com.arabam.android.assigment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.data.paging.AdvertsPagingSource
import com.arabam.android.assigment.data.service.Api
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdvertRepoImpl @Inject constructor(
    private val api:Api
):AdvertRepo {

    override fun allAdverts(sort: Int?, direction: Int?,minYear:Int?,maxYear:Int?): Flow<PagingData<ListingAdvert>> =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false, maxSize = 100),
            pagingSourceFactory = { AdvertsPagingSource(api,sort,direction,minYear,maxYear) }
        ).flow
}