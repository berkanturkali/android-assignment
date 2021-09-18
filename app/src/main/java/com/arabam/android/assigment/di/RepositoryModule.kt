package com.arabam.android.assigment.di

import com.arabam.android.assigment.data.repository.AdvertRepo
import com.arabam.android.assigment.data.repository.AdvertRepoImpl
import com.arabam.android.assigment.data.repository.DbRepo
import com.arabam.android.assigment.data.repository.DbRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAdvertRepo(advertRepoImpl: AdvertRepoImpl): AdvertRepo

    @Binds
    abstract fun bindDbtRepo(dbRepoImpl: DbRepoImpl): DbRepo
}