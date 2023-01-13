package com.arabam.android.assignment.core.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public object ApiServiceFactory {

    public fun makeApiService(gson: Gson,baseUrl:String): ApiService {
        val okHttpClient = makeOkHttpClient(makeHttpLoggingInterceptor())
        return makeApiService(gson, okHttpClient,baseUrl)
    }

    private fun makeApiService(gson: Gson, okHttpClient: OkHttpClient,baseUrl: String): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun makeHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
    }.build()
}
