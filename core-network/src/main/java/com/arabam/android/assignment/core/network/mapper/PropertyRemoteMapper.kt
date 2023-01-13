package com.arabam.android.assignment.core.network.mapper

import com.arabam.android.assignment.core.model.Property
import com.arabam.android.assignment.core.network.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.core.network.model.PropertyDto
import javax.inject.Inject

public class PropertyRemoteMapper @Inject constructor() : RemoteModelMapper<PropertyDto, Property> {
    override fun mapFromModel(model: PropertyDto): Property {
        return Property(
            name = model.name,
            value = model.value
        )
    }
}
