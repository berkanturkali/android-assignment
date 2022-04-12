package com.arabam.android.assignment.remote.mapper

import com.arabam.android.assignment.remote.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.remote.model.Location
import com.arabam.android.assignment.remote.model.LocationDto
import javax.inject.Inject

public class LocationRemoteMapper @Inject constructor() : RemoteModelMapper<LocationDto, Location> {
    override fun mapFromModel(model: LocationDto): Location {
        return Location(
            cityName = model.cityName,
            townName = model.townName
        )
    }
}
