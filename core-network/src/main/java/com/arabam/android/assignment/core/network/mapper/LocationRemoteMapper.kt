package com.arabam.android.assignment.core.network.mapper

import com.arabam.android.assignment.core.model.Location
import com.arabam.android.assignment.core.network.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.core.network.model.LocationDto
import javax.inject.Inject

public class LocationRemoteMapper @Inject constructor() : RemoteModelMapper<LocationDto, Location> {
    override fun mapFromModel(model: LocationDto): Location {
        return Location(
            cityName = model.cityName,
            townName = model.townName
        )
    }
}
