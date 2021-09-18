package com.arabam.android.assigment.data.repository

import androidx.paging.PagingData
import com.arabam.android.assigment.data.model.ListingAdvert
import kotlinx.coroutines.flow.Flow

interface AdvertRepo {
    fun allAdverts(sort:Int?,direction:Int?,minYear:Int?,maxYear:Int?): Flow<PagingData<ListingAdvert>>
}