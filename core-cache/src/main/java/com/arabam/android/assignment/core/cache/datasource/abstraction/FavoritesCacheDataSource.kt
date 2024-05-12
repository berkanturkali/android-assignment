package com.arabam.android.assignment.core.cache.datasource.abstraction

import androidx.room.Delete
import com.arabam.android.assignment.core.cache.data.entity.FavoriteAdvertEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesCacheDataSource {

    suspend fun upsertFavorite(advert: FavoriteAdvertEntity): Long

    @Delete
    suspend fun deleteFavorite(advert: FavoriteAdvertEntity): Int

    suspend fun getAdvert(id: Int): FavoriteAdvertEntity

    fun getFavorites(): Flow<List<FavoriteAdvertEntity>>
}