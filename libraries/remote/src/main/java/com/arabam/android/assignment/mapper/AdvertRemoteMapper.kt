package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.ListingAdvert
import com.arabam.android.assignment.model.ListingAdvertDto
import javax.inject.Inject

class AdvertRemoteMapper @Inject constructor(
    private val categoryRemoteMapper: CategoryRemoteMapper,
    private val locationRemoteMapper: LocationRemoteMapper,
    private val propertyRemoteMapper: PropertyRemoteMapper,
) : RemoteModelMapper<ListingAdvertDto, ListingAdvert> {
    override fun mapFromModel(model: ListingAdvertDto): ListingAdvert {
        return ListingAdvert(
            category = categoryRemoteMapper.mapFromModel(model.category),
            date = model.date,
            dateFormatted = model.dateFormatted,
            id = model.id,
            location = locationRemoteMapper.mapFromModel(model.location),
            modelName = model.modelName,
            photo = model.photo,
            price = model.price,
            priceFormatted = model.priceFormatted,
            properties = propertyRemoteMapper.mapModelList(model.properties),
            title = model.title
        )
    }
}
