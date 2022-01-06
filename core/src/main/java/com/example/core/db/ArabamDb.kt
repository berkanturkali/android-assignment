package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arabam.android.assignment.database.dao.AdvertDao
import com.arabam.android.assignment.database.dao.LastVisitedAdvertsDao
import com.arabam.android.assignment.database.data.entity.AdvertEntity
import com.arabam.android.assignment.database.data.entity.VisitedAdvert

@Database(
    entities = [AdvertEntity::class,VisitedAdvert::class],
    version = 1,
    exportSchema = false
)
abstract class ArabamDb : RoomDatabase() {
    abstract fun dao(): AdvertDao
    abstract fun lastVisitedItemsDao(): LastVisitedAdvertsDao
}