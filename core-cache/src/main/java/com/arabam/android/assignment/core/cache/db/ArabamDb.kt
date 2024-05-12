package com.arabam.android.assignment.core.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arabam.android.assignment.core.cache.BuildConfig
import com.arabam.android.assignment.core.cache.data.entity.FavoriteAdvertEntity
import com.arabam.android.assignment.core.cache.data.entity.LastVisitedAdvertEntity
import com.arabam.android.assignment.core.cache.dao.FavoritesDao
import com.arabam.android.assignment.core.cache.dao.LastVisitedAdvertsDao

@Database(
    entities = [FavoriteAdvertEntity::class, LastVisitedAdvertEntity::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class ArabamDb : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
    abstract fun lastVisitedItemsDao(): LastVisitedAdvertsDao

    companion object {
        fun build(context: Context): ArabamDb = Room.databaseBuilder(
            context.applicationContext,
            ArabamDb::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
