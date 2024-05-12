package com.arabam.android.assignment.core.cache.dao

import androidx.room.*
import com.arabam.android.assignment.core.cache.data.entity.FavoriteAdvertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(advert: FavoriteAdvertEntity): Long

    @Delete
    suspend fun delete(advert: FavoriteAdvertEntity): Int

    @Query("SELECT * FROM FavoriteAdvertEntity WHERE id =:id")
    suspend fun getAdvert(id: Int): FavoriteAdvertEntity

    @Query("SELECT * FROM FavoriteAdvertEntity")
    fun getFavorites(): Flow<List<FavoriteAdvertEntity>>
}
