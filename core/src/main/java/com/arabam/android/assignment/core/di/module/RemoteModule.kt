package com.arabam.android.assignment.core.di.module

import com.arabam.android.assignment.remote.ApiService
import com.arabam.android.assignment.remote.ApiServiceFactory
import com.example.core.BuildConfig
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
            return ApiServiceFactory.makeApiService(gson, baseUrl = BuildConfig.BASE_URL)
        }
    }
}
