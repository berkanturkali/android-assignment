package com.arabam.android.assignment.core.cache.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.arabam.android.assignment.core.cache.data.entity.VisitedAdvert

@Dao
public interface LastVisitedAdvertsDao {

    @Insert(onConflict = REPLACE)
    public suspend fun insert(advert: VisitedAdvert): Long

    @Delete
    public suspend fun delete(advert: VisitedAdvert): Int

    @Query("DELETE FROM last_visited_adverts WHERE visitedAt = (SELECT MIN(visitedAt) FROM last_visited_adverts)")
    public suspend fun deleteLastItem()

    @Query("SELECT * FROM last_visited_adverts ORDER BY visitedAt DESC")
    public suspend fun lastAdverts(): List<VisitedAdvert>

    @Query("SELECT COUNT(id) FROM last_visited_adverts")
    public suspend fun getItemCount(): Int

    @Transaction
    public suspend fun insertAndDeleteTransaction(advert: VisitedAdvert) {
        val count = getItemCount()
        if (count == 10) {
            insert(advert)
            deleteLastItem()
        } else {
            insert(advert)
        }
    }
}
