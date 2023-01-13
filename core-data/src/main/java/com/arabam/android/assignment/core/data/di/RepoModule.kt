package com.arabam.android.assignment.core.data.di

import com.arabam.android.assignment.core.data.repo.abstraction.AdvertRepo
import com.arabam.android.assignment.core.data.repo.implementation.AdvertRepoImpl
import com.arabam.android.assignment.core.data.repo.implementation.DbRepoImpl
import com.arabam.android.assignment.core.data.repo.abstraction.DbRepo
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
