package com.arabam.android.assignment.core.db

import android.content.Context
import android.os.Build
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arabam.android.assignment.database.dao.AdvertDao
import com.arabam.android.assignment.database.dao.LastVisitedAdvertsDao
import com.arabam.android.assignment.database.data.entity.AdvertEntity
import com.arabam.android.assignment.database.data.entity.VisitedAdvert
import com.example.core.BuildConfig

@Database(
    entities = [AdvertEntity::class, VisitedAdvert::class],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class ArabamDb : RoomDatabase() {
    abstract fun dao(): AdvertDao
    abstract fun lastVisitedItemsDao(): LastVisitedAdvertsDao

    companion object {
        fun build(context: Context): ArabamDb = Room.databaseBuilder(
            context.applicationContext,
            ArabamDb::class.java,
            BuildConfig.databaseName
        )
            .fallbackToDestructiveMigration().build()
    }
}
