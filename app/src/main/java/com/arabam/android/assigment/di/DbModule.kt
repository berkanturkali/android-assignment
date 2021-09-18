package com.arabam.android.assigment.di

import android.content.Context
import androidx.room.Room
import com.arabam.android.assigment.db.ArabamDb
import com.arabam.android.assigment.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): ArabamDb =
        Room.databaseBuilder(context,ArabamDb::class.java,DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(db:ArabamDb) = db.dao()
}