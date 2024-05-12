package com.arabam.android.assignment.core.cache.di

import com.arabam.android.assignment.core.cache.datasource.abstraction.FavoritesCacheDataSource
import com.arabam.android.assignment.core.cache.datasource.abstraction.LastVisitedAdvertsCacheDataSource
import com.arabam.android.assignment.core.cache.datasource.implementation.FavoritesCacheDataSourceImpl
import com.arabam.android.assignment.core.cache.datasource.implementation.LastVisitedAdvertsCacheDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface CacheDataSourceModule {

    @get:Binds
    val FavoritesCacheDataSourceImpl.favoritesCacheDataSource: FavoritesCacheDataSource

    @get:Binds
    val LastVisitedAdvertsCacheDataSourceImpl.lastVisitedAdvertsCacheDataSource: LastVisitedAdvertsCacheDataSource

}