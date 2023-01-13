package com.arabam.android.assignment.core.network.di

import com.arabam.android.assignment.core.network.ApiService
import com.arabam.android.assignment.core.network.ApiServiceFactory
import com.arabam.android.assignment.core.network.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RemoteModule {
    companion object {
        @get:[Provides Singleton]
        val gson: Gson
            get() = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .serializeNulls()
                .setLenient()
                .create()

        @[Provides Singleton]
        fun provideApiService(gson: Gson): ApiService {
            return ApiServiceFactory.makeApiService(
                gson, baseUrl = BuildConfig.BASE_URL
            )
        }
    }
}
