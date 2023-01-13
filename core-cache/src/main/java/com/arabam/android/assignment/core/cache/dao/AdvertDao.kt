package com.arabam.android.assignment.core.cache.dao

import androidx.room.*
import com.arabam.android.assignment.core.cache.data.entity.AdvertEntity
import kotlinx.coroutines.flow.Flow

@Dao
public interface AdvertDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public suspend fun upsert(advert: AdvertEntity): Long

    @Delete
    public suspend fun delete(advert: AdvertEntity): Int

    @Query("SELECT * FROM AdvertEntity WHERE id =:id")
    public suspend fun getAdvert(id: Int): AdvertEntity

    @Query("SELECT * FROM AdvertEntity")
    public fun getFavs(): Flow<List<AdvertEntity>>
}
