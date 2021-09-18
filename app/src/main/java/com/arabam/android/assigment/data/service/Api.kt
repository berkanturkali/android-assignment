package com.arabam.android.assigment.data.service

import com.arabam.android.assigment.data.model.ListingAdvert
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/v1/listing")
    suspend fun allAdverts(
        @Query("skip") skip: Int,
        @Query("take") take: Int = 10,
        @Query("sort") sort: Int?,
        @Query("sortDirection") direction: Int?,
        @Query("minYear") min:Int?,
        @Query("maxYear") max:Int?
    ): List<ListingAdvert>
}