package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.Category
import com.arabam.android.assignment.model.CategoryDto
import javax.inject.Inject

class CategoryRemoteMapper @Inject constructor() : RemoteModelMapper<CategoryDto, Category> {

    override fun mapFromModel(model: CategoryDto): Category {
        return Category(
            id = model.id,
            name = model.name
        )
    }
}
