package com.arabam.android.assignment.domain.data.repo

import androidx.paging.PagingData
import com.arabam.android.assignment.remote.model.DetailAdvert
import com.arabam.android.assignment.remote.model.ListingAdvert
import com.arabam.android.assignment.remote.model.Resource
import kotlinx.coroutines.flow.Flow

public interface AdvertRepo {
    public fun allAdverts(
        categoryId: Int?,
        sort: Int?,
        direction: Int?,
        minYear: Int?,
        maxYear: Int?,
    ): Flow<PagingData<ListingAdvert>>

    public suspend fun advert(id: Int): Resource<DetailAdvert>
}
