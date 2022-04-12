package com.arabam.android.assignment.core.di.module

import com.arabam.android.assignment.domain.data.repo.AdvertRepo
import com.arabam.android.assignment.domain.data.repo.AdvertRepoImpl
import com.arabam.android.assignment.domain.data.repo.DbRepo
import com.arabam.android.assignment.domain.data.repo.DbRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepoModule {

    @get:[Binds Singleton]
    val AdvertRepoImpl.advertRepo: AdvertRepo

    @get:[Binds Singleton]
    val DbRepoImpl.dbRepo: DbRepo
}
