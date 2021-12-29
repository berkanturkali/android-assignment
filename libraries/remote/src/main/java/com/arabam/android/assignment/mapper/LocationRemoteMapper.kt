package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.listing.model.LocationDto
import javax.inject.Inject

class LocationRemoteMapper @Inject constructor() : RemoteModelMapper<LocationDto, com.arabam.android.assignment.listing.model.Location> {
    override fun mapFromModel(model: LocationDto): com.arabam.android.assignment.listing.model.Location {
        return com.arabam.android.assignment.listing.model.Location(
            cityName = model.cityName,
            townName = model.townName
        )
    }
}