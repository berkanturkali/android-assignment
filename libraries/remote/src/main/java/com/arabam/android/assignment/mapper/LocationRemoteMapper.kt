package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.Location
import com.arabam.android.assignment.model.LocationDto
import javax.inject.Inject

class LocationRemoteMapper @Inject constructor() : RemoteModelMapper<LocationDto, Location> {
    override fun mapFromModel(model: LocationDto): Location {
        return Location(
            cityName = model.cityName,
            townName = model.townName
        )
    }
}
