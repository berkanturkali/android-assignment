package com.arabam.android.assignment.core.data.repo.implementation

import com.arabam.android.assignment.core.cache.dao.FavoritesDao
import com.arabam.android.assignment.core.cache.dao.LastVisitedAdvertsDao
import com.arabam.android.assignment.core.cache.data.mapper.DbEntityMapper
import com.arabam.android.assignment.core.cache.data.mapper.VisitedEntityMapper
import com.arabam.android.assignment.core.data.repo.abstraction.DbRepo
import com.arabam.android.assignment.core.model.ListingAdvert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepoImpl @Inject constructor(
    private val mapper: DbEntityMapper,
    private val visitedMapper: VisitedEntityMapper,
    private val lastVisitedDao: LastVisitedAdvertsDao,
    private val favoritesDao: FavoritesDao,
) : DbRepo {
    override suspend fun addToFav(advert: ListingAdvert): Long {
        val entity = mapper.fromDomain(advert)
        return favoritesDao.upsert(entity)
    }

    override suspend fun removeFromFav(advert: ListingAdvert): Int {
        val entity = mapper.fromDomain(advert)
        return favoritesDao.delete(entity)
    }

    override suspend fun getAdvert(id: Int): ListingAdvert? {
        val entity = favoritesDao.getAdvert(id)
        return mapper.toDomain(entity)
    }

    override suspend fun favorites(): Flow<List<ListingAdvert>> {
        return favoritesDao.getFavorites().map {
            mapper.toDomainList(it)
        }
    }

    override suspend fun insertToLastVisited(advert: ListingAdvert) {
        val entity = visitedMapper.fromDomain(advert)
        lastVisitedDao.insertAndDeleteTransaction(entity)
    }

    override suspend fun getLastVisitedItems(): List<ListingAdvert> {
        return visitedMapper.toDomainList(lastVisitedDao.lastAdverts())
    }
}
