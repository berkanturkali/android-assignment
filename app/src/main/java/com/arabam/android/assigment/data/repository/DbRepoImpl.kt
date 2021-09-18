package com.arabam.android.assigment.data.repository

import com.arabam.android.assigment.data.DbEntityMapper
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.db.dao.AdvertDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepoImpl @Inject constructor(
    private val mapper:DbEntityMapper,
    private val dao:AdvertDao
):DbRepo {
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