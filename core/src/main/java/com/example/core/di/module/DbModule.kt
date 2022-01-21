package com.example.core.di.module

import android.content.Context
import androidx.room.Room
import com.example.core.db.ArabamDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    private const val DB_NAME = "arabam_db"

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): ArabamDb =
        Room.databaseBuilder(context, ArabamDb::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(db: ArabamDb) = db.dao()

    @Provides
    fun provideLastVisitedItemsDao(db: ArabamDb) = db.lastVisitedItemsDao()
}
