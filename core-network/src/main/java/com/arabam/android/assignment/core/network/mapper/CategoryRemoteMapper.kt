package com.arabam.android.assignment.core.network.mapper

import com.arabam.android.assignment.core.model.Category
import com.arabam.android.assignment.core.network.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.core.network.model.CategoryDto
import javax.inject.Inject

public class CategoryRemoteMapper @Inject constructor() : RemoteModelMapper<CategoryDto, Category> {

    override fun mapFromModel(model: CategoryDto): Category {
        return Category(
            id = model.id,
            name = model.name
        )
    }
}
