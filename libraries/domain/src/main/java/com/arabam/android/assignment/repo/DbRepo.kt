package com.arabam.android.assignment.repo

import com.arabam.android.assignment.detail.DetailAdvert
import com.arabam.android.assignment.listing.model.ListingAdvert
import kotlinx.coroutines.flow.Flow

interface DbRepo {

    suspend fun addToFav(advert: ListingAdvert):Long

    suspend fun removeFromFav(advert: ListingAdvert):Int

    suspend fun getAdvert(id:Int):ListingAdvert?

    suspend fun favorites(): Flow<List<ListingAdvert>>

    suspend fun insertToLastVisited(advert:ListingAdvert)

    suspend fun getLastVisitedItems():List<ListingAdvert>
}