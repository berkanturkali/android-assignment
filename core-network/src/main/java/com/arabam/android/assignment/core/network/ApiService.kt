package com.arabam.android.assignment.core.network

import com.arabam.android.assignment.core.network.model.DetailAdvertDto
import com.arabam.android.assignment.core.network.model.ListingAdvertDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiService {

    @GET("api/v1/listing")
    public suspend fun allAdverts(
        @Query("skip") skip: Int,
        @Query("take") take: Int = 10,
        @Query("categoryId") id: Int?,
        @Query("sort") sort: Int?,
        @Query("sortDirection") direction: Int?,
        @Query("minYear") min: Int?,
        @Query("maxYear") max: Int?,
    ): List<ListingAdvertDto>

    @GET("api/v1/detail")
    public suspend fun advert(
        @Query("id") id: Int,
    ): Response<DetailAdvertDto>
}
