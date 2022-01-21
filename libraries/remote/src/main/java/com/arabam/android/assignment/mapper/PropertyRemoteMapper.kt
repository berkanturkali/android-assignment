package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.Property
import com.arabam.android.assignment.model.PropertyDto
import javax.inject.Inject

class PropertyRemoteMapper @Inject constructor() : RemoteModelMapper<PropertyDto, Property> {
    override fun mapFromModel(model: PropertyDto): Property {
        return Property(
            name = model.name,
            value = model.value
        )
    }
}
