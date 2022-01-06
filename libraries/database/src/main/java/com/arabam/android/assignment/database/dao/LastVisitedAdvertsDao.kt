package com.arabam.android.assignment.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.arabam.android.assignment.database.data.entity.VisitedAdvert

@Dao
interface LastVisitedAdvertsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(advert: VisitedAdvert): Long

    @Delete
    suspend fun delete(advert: VisitedAdvert): Int

    @Query("DELETE FROM last_visited_adverts WHERE visitedAt = (SELECT MIN(visitedAt) FROM last_visited_adverts)")
    suspend fun deleteLastItem()

    @Query("SELECT * FROM last_visited_adverts ORDER BY visitedAt DESC")
    suspend fun lastAdverts(): List<VisitedAdvert>

    @Query("SELECT COUNT(id) FROM last_visited_adverts")
    fun getItemCount(): Int

    @Transaction
    suspend fun insertAndDeleteTransaction(advert: VisitedAdvert) {
        val count = getItemCount()
        if (count == 10) {
            insert(advert)
            deleteLastItem()
        } else {
            insert(advert)
        }
    }
}