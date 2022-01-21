package com.arabam.android.assignment

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceFactory {

    private const val BASE_URL = "http://sandbox.arabamd.com/"

    fun makeApiService(gson: Gson): ApiService {
        val okHttpClient = makeOkHttpClient(makeHttpLoggingInterceptor())
        return makeApiService(gson, okHttpClient)
    }

    private fun makeApiService(gson: Gson, okHttpClient: OkHttpClient): ApiService {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
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
