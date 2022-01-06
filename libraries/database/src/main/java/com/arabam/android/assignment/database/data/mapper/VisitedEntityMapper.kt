package com.arabam.android.assignment.database.data.mapper

import com.arabam.android.assignment.database.data.entity.VisitedAdvert
import com.arabam.android.assignment.listing.model.Category
import com.arabam.android.assignment.listing.model.ListingAdvert
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VisitedEntityMapper @Inject constructor(): Mapper<VisitedAdvert, ListingAdvert> {
    override fun toDomain(entity: VisitedAdvert?): ListingAdvert? {
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

    override fun fromDomain(domain: ListingAdvert): VisitedAdvert {
        return VisitedAdvert(
            id = domain.id,
            location = domain.location,
            photo = domain.photo,
            price = domain.price,
            model = domain.modelName
        )
    }

    override fun toDomainList(list: List<VisitedAdvert>?): List<ListingAdvert> {
        return if (!list.isNullOrEmpty()) {
            list.map { toDomain(it)!! }
        } else {
            emptyList()
        }
    }
}