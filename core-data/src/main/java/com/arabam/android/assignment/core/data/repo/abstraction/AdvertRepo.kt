package com.arabam.android.assignment.core.data.repo.abstraction

import androidx.paging.PagingData
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.model.Resource
import kotlinx.coroutines.flow.Flow

interface AdvertRepo {
    fun fetchAllAdverts(
        categoryId: Int?,
        sort: Int?,
        direction: Int?,
        minYear: Int?,
        maxYear: Int?,
    ): Flow<PagingData<ListingAdvert>>

    suspend fun fetchAdvert(id: Int): Resource<DetailAdvert>
}
