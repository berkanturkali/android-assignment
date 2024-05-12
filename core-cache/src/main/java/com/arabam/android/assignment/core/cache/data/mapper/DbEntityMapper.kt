package com.arabam.android.assignment.core.cache.data.mapper

import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.cache.data.entity.FavoriteAdvertEntity
import com.arabam.android.assignment.core.model.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbEntityMapper @Inject constructor() :
    Mapper<FavoriteAdvertEntity, ListingAdvert> {

    override fun toDomain(entity: FavoriteAdvertEntity?): ListingAdvert? {
        return entity?.let {
            ListingAdvert(
                id = it.id,
                location = it.location,
                price = it.price,
                title = it.title,
                category = Category(-1, ""),
                properties = emptyList(),
                date = "",
                dateFormatted = "",
                modelName = "",
                photo = entity.photo,
                priceFormatted = "",
            )
        }
    }

    override fun fromDomain(domain: ListingAdvert): FavoriteAdvertEntity {
        return FavoriteAdvertEntity(
            id = domain.id,
            location = domain.location,
            photo = domain.photo,
            price = domain.price,
            title = domain.title
        )
    }

    override fun toDomainList(list: List<FavoriteAdvertEntity>?): List<ListingAdvert> {
        return if (!list.isNullOrEmpty()) {
            list.map { toDomain(it)!! }
        } else {
            emptyList()
        }
    }
}
