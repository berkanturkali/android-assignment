package com.arabam.android.assignment.remote.mapper

import com.arabam.android.assignment.remote.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.remote.model.Category
import com.arabam.android.assignment.remote.model.CategoryDto
import javax.inject.Inject

public class CategoryRemoteMapper @Inject constructor() : RemoteModelMapper<CategoryDto, Category> {

    override fun mapFromModel(model: CategoryDto): Category {
        return Category(
            id = model.id,
            name = model.name
        )
    }
}
