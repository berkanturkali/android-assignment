package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.listing.model.CategoryDto
import javax.inject.Inject

class CategoryRemoteMapper @Inject constructor():RemoteModelMapper<CategoryDto, com.arabam.android.assignment.listing.model.Category>{

    override fun mapFromModel(model: CategoryDto): com.arabam.android.assignment.listing.model.Category {
        return com.arabam.android.assignment.listing.model.Category(
            id = model.id,
            name = model.name
        )
    }
}