package com.example.core.di.module

import com.arabam.android.assignment.repo.AdvertRepo
import com.arabam.android.assignment.repo.AdvertRepoImpl
import com.arabam.android.assignment.repo.DbRepo
import com.arabam.android.assignment.repo.DbRepoImpl
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
