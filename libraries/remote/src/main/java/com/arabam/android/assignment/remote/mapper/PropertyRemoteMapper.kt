package com.arabam.android.assignment.remote.mapper

import com.arabam.android.assignment.remote.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.remote.model.Property
import com.arabam.android.assignment.remote.model.PropertyDto
import javax.inject.Inject

public class PropertyRemoteMapper @Inject constructor() : RemoteModelMapper<PropertyDto, Property> {
    override fun mapFromModel(model: PropertyDto): Property {
        return Property(
            name = model.name,
            value = model.value
        )
    }
}
