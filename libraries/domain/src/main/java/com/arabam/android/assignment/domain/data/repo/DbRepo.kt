package com.arabam.android.assignment.domain.data.repo

import com.arabam.android.assignment.remote.model.ListingAdvert
import kotlinx.coroutines.flow.Flow

public interface DbRepo {

    public suspend fun addToFav(advert: ListingAdvert): Long

    public suspend fun removeFromFav(advert: ListingAdvert): Int

    public suspend fun getAdvert(id: Int): ListingAdvert?

    public suspend fun favorites(): Flow<List<ListingAdvert>>

    public suspend fun insertToLastVisited(advert: ListingAdvert)

    public suspend fun getLastVisitedItems(): List<ListingAdvert>
}
