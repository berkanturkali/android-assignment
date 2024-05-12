package com.arabam.android.assignment.core.cache.datasource.abstraction

import com.arabam.android.assignment.core.cache.data.entity.LastVisitedAdvertEntity

interface LastVisitedAdvertsCacheDataSource {
    suspend fun insertAdvert(advert: LastVisitedAdvertEntity): Long

    suspend fun deleteAdvert(advert: LastVisitedAdvertEntity): Int
    suspend fun deleteLastAdvert()

    suspend fun fetchLastVisitedAdverts(): List<LastVisitedAdvertEntity>

    suspend fun getItemCount(): Int
    suspend fun insertAndDeleteAdvertTransaction(advert: LastVisitedAdvertEntity)
}