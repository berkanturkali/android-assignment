package com.arabam.android.assignment.core.cache.data.mapper

import com.arabam.android.assignment.core.cache.data.entity.LastVisitedAdvertEntity
import com.arabam.android.assignment.core.model.Category
import com.arabam.android.assignment.core.model.ListingAdvert
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
public class VisitedEntityMapper @Inject constructor() :
    Mapper<LastVisitedAdvertEntity, ListingAdvert> {
    override fun toDomain(entity: LastVisitedAdvertEntity?): ListingAdvert? {
        return entity?.let {
            ListingAdvert(
                id = it.id,
                location = it.location,
                price = it.price,
                title = "",
                category = Category(-1, ""),
                properties = emptyList(),
                date = "",
                dateFormatted = "",
                modelName = it.model,
                photo = entity.photo,
                priceFormatted = "",
            )
        }
    }

    override fun fromDomain(domain: ListingAdvert): LastVisitedAdvertEntity {
        return LastVisitedAdvertEntity(
            id = domain.id,
            location = domain.location,
            photo = domain.photo,
            price = domain.price,
            model = domain.modelName,
        )
    }

    override fun toDomainList(list: List<LastVisitedAdvertEntity>?): List<ListingAdvert> {
        return if (!list.isNullOrEmpty()) {
            list.map { toDomain(it)!! }
        } else {
            emptyList()
        }
    }
}
