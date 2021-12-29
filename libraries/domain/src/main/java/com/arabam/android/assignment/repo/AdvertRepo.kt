package com.arabam.android.assignment.repo

import androidx.paging.PagingData
import com.arabam.android.assignment.detail.DetailAdvert
import com.arabam.android.assignment.listing.model.ListingAdvert
import com.arabam.android.assignment.listing.model.Resource
import kotlinx.coroutines.flow.Flow

interface AdvertRepo {
    fun allAdverts(
        sort: Int?,
        direction: Int?,
        minYear: Int?,
        maxYear: Int?,
    ): Flow<PagingData<ListingAdvert>>

    suspend fun advert(id: Int): Resource<DetailAdvert>
}