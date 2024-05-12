package com.arabam.android.assignment.core.cache.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.arabam.android.assignment.core.cache.data.entity.LastVisitedAdvertEntity

@Dao
interface LastVisitedAdvertsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(advert: LastVisitedAdvertEntity): Long

    @Delete
    suspend fun delete(advert: LastVisitedAdvertEntity): Int

    @Query("DELETE FROM last_visited_adverts WHERE visitedAt = (SELECT MIN(visitedAt) FROM last_visited_adverts)")
    suspend fun deleteLastItem()

    @Query("SELECT * FROM last_visited_adverts ORDER BY visitedAt DESC")
    suspend fun lastAdverts(): List<LastVisitedAdvertEntity>

    @Query("SELECT COUNT(id) FROM last_visited_adverts")
    suspend fun getItemCount(): Int

    @Transaction
    suspend fun insertAndDeleteTransaction(advert: LastVisitedAdvertEntity) {
        val count = getItemCount()
        if (count == 10) {
            insert(advert)
            deleteLastItem()
        } else {
            insert(advert)
        }
    }
}
