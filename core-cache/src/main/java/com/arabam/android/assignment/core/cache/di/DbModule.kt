package com.arabam.android.assignment.core.cache.di

import android.content.Context
import com.arabam.android.assignment.core.cache.db.ArabamDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @[Provides Singleton]
    fun provideDb(@ApplicationContext context: Context): ArabamDb = ArabamDb.build(context)

    @Provides
    fun provideDao(db: ArabamDb) = db.dao()

    @Provides
    fun provideLastVisitedItemsDao(db: ArabamDb) = db.lastVisitedItemsDao()
}
