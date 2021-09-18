package com.arabam.android.assigment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arabam.android.assigment.db.dao.AdvertDao
import com.arabam.android.assigment.db.data.entity.AdvertEntity

@Database(
    entities = [AdvertEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArabamDb:RoomDatabase() {
    abstract fun dao(): AdvertDao
}