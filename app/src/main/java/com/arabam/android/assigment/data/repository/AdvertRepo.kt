package com.arabam.android.assigment.data.repository

import androidx.paging.PagingData
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.data.model.ListingAdvert
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AdvertRepo {
    fun allAdverts(sort:Int?,direction:Int?,minYear:Int?,maxYear:Int?): Flow<PagingData<ListingAdvert>>

    suspend fun advert(id:Int): Response<DetailAdvert>
}