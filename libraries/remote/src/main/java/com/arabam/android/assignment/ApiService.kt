package com.arabam.android.assignment

import com.arabam.android.assignment.listing.model.DetailAdvertDto
import com.arabam.android.assignment.listing.model.ListingAdvertDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/listing")
    suspend fun allAdverts(
        @Query("skip") skip: Int,
        @Query("take") take: Int = 10,
        @Query("sort") sort: Int?,
        @Query("sortDirection") direction: Int?,
        @Query("minYear") min: Int?,
        @Query("maxYear") max: Int?,
    ): List<ListingAdvertDto>

    @GET("api/v1/detail")
    suspend fun advert(
        @Query("id") id: Int,
    ): Response<DetailAdvertDto>
}