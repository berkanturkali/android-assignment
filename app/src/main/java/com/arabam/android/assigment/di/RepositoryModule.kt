package com.arabam.android.assigment.di

import com.arabam.android.assigment.data.repository.AdvertRepo
import com.arabam.android.assigment.data.repository.AdvertRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAdvertRepo(advertRepoImpl: AdvertRepoImpl): AdvertRepo
}