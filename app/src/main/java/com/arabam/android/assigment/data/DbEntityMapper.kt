package com.arabam.android.assigment.data

import com.arabam.android.assigment.data.model.Category
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.db.data.entity.AdvertEntity

class DbEntityMapper:Mapper<AdvertEntity,ListingAdvert> {

    override fun toDomain(entity: AdvertEntity?): ListingAdvert? {
        return entity?.let {
            ListingAdvert(
                date = it.date,
                dateFormatted = it.dateFormatted,
                id = it.id,
                location = it.location,
                modelName = it.modelName,
                photo = it.photo,
                price = it.price,
                priceFormatted = it.priceFormatted,
                title = it.title,
                category = Category(-1,""),
                properties = emptyList()
            )
        }
    }

    override fun fromDomain(domain: ListingAdvert): AdvertEntity {
        return AdvertEntity(
            date = domain.date,
            dateFormatted = domain.dateFormatted,
            id = domain.id,
            location = domain.location,
            modelName = domain.modelName,
            photo = domain.photo,
            price = domain.price,
            priceFormatted = domain.priceFormatted,
            title = domain.title
        )
    }

    override fun toDomainList(list: List<AdvertEntity>?): List<ListingAdvert> {
        return if (!list.isNullOrEmpty()) {
            list.map { toDomain(it)!! }
        } else {
            emptyList()
        }
    }
}