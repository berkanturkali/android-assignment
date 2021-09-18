package com.arabam.android.assigment.data.repository

import com.arabam.android.assigment.data.model.ListingAdvert
import kotlinx.coroutines.flow.Flow

interface DbRepo {

    suspend fun addToFav(advert: ListingAdvert):Long

    suspend fun removeFromFav(advert: ListingAdvert):Int

    suspend fun getAdvert(id:Int):ListingAdvert?

    suspend fun favorites(): Flow<List<ListingAdvert>>
}