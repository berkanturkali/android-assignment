package com.arabam.android.assignment.core.cache.datasource.implementation

import com.arabam.android.assignment.core.cache.dao.LastVisitedAdvertsDao
import com.arabam.android.assignment.core.cache.data.entity.LastVisitedAdvertEntity
import com.arabam.android.assignment.core.cache.datasource.abstraction.LastVisitedAdvertsCacheDataSource
import javax.inject.Inject

class LastVisitedAdvertsCacheDataSourceImpl @Inject constructor(
    private val lastVisitedAdvertsDao: LastVisitedAdvertsDao
) : LastVisitedAdvertsCacheDataSource {
    override suspend fun insertAdvert(advert: LastVisitedAdvertEntity): Long {
        return lastVisitedAdvertsDao.insert(advert)
    }

    override suspend fun deleteAdvert(advert: LastVisitedAdvertEntity): Int {
        return lastVisitedAdvertsDao.delete(advert)
    }

    override suspend fun deleteLastAdvert() {
        lastVisitedAdvertsDao.deleteLastItem()
    }

    override suspend fun fetchLastVisitedAdverts(): List<LastVisitedAdvertEntity> {
        return lastVisitedAdvertsDao.lastAdverts()
    }

    override suspend fun getItemCount(): Int {
        return lastVisitedAdvertsDao.getItemCount()
    }

    override suspend fun insertAndDeleteAdvertTransaction(advert: LastVisitedAdvertEntity) {
        lastVisitedAdvertsDao.insertAndDeleteTransaction(advert)
    }
}