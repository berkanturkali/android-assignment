package com.arabam.android.assignment.database.dao

import androidx.room.*
import com.arabam.android.assignment.database.data.entity.AdvertEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AdvertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(advert: AdvertEntity): Long

    @Delete
    suspend fun delete(advert: AdvertEntity): Int

    @Query("SELECT * FROM AdvertEntity WHERE id =:id")
    suspend fun getAdvert(id: Int): AdvertEntity

    @Query("SELECT * FROM AdvertEntity")
    fun getFavs(): Flow<List<AdvertEntity>>
}
