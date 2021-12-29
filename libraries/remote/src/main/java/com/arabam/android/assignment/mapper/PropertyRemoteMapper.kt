package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.listing.model.PropertyDto
import javax.inject.Inject

class PropertyRemoteMapper @Inject constructor():RemoteModelMapper<PropertyDto, com.arabam.android.assignment.listing.model.Property>{
    override fun mapFromModel(model: PropertyDto): com.arabam.android.assignment.listing.model.Property {
        return com.arabam.android.assignment.listing.model.Property(
            name = model.name,
            value = model.value
        )
    }
}