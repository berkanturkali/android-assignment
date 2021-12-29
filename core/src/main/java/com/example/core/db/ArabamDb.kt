package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arabam.android.assignment.database.dao.AdvertDao
import com.arabam.android.assignment.database.data.entity.AdvertEntity

@Database(
    entities = [AdvertEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArabamDb : RoomDatabase() {
    abstract fun dao(): AdvertDao
}