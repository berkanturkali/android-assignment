package com.arabam.android.assignment.repo

import com.arabam.android.assignment.database.dao.AdvertDao
import com.arabam.android.assignment.database.data.mapper.DbEntityMapper
import com.arabam.android.assignment.detail.DetailAdvert
import com.arabam.android.assignment.listing.model.ListingAdvert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepoImpl @Inject constructor(
    private val mapper: DbEntityMapper,
    private val dao: AdvertDao,
) : DbRepo {
    override suspend fun addToFav(advert: ListingAdvert): Long {
        val entity = mapper.fromDomain(advert)
        return dao.upsert(entity)
    }

    override suspend fun removeFromFav(advert: ListingAdvert): Int {
        val entity = mapper.fromDomain(advert)
        return dao.delete(entity)
    }

    override suspend fun getAdvert(id: Int): ListingAdvert? {
        val entity = dao.getAdvert(id)
        return mapper.toDomain(entity)
    }

    override suspend fun favorites(): Flow<List<ListingAdvert>> {
        return dao.getFavs().map {
            mapper.toDomainList(it)
        }
    }
}