package com.arabam.android.assignment.core.cache.datasource.implementation

import com.arabam.android.assignment.core.cache.dao.FavoritesDao
import com.arabam.android.assignment.core.cache.data.entity.FavoriteAdvertEntity
import com.arabam.android.assignment.core.cache.datasource.abstraction.FavoritesCacheDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesCacheDataSourceImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
) : FavoritesCacheDataSource {
    override suspend fun upsertFavorite(advert: FavoriteAdvertEntity): Long {
        return favoritesDao.upsert(advert)
    }

    override suspend fun deleteFavorite(advert: FavoriteAdvertEntity): Int {
        return favoritesDao.delete(advert)
    }

    override suspend fun getAdvert(id: Int): FavoriteAdvertEntity {
        return favoritesDao.getAdvert(id)
    }

    override fun getFavorites(): Flow<List<FavoriteAdvertEntity>> {
        return favoritesDao.getFavorites()
    }
}