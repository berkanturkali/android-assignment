package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.DetailAdvert
import com.arabam.android.assignment.model.DetailAdvertDto
import javax.inject.Inject

class DetailAdvertMapper @Inject constructor(
    private val categoryRemoteMapper: CategoryRemoteMapper,
    private val propertyRemoteMapper: PropertyRemoteMapper,
    private val userInfoRemoteMapper: UserInfoRemoteMapper,
) : RemoteModelMapper<DetailAdvertDto, DetailAdvert> {

    override fun mapFromModel(model: DetailAdvertDto): DetailAdvert {
        return DetailAdvert(
            category = categoryRemoteMapper.mapFromModel(model.category),
            date = model.date,
            dateFormatted = model.dateFormatted,
            id = model.id,
            location = model.location,
            modelName = model.modelName,
            photos = model.photos,
            price = model.price,
            priceFormatted = model.priceFormatted,
            properties = propertyRemoteMapper.mapModelList(model.properties),
            text = model.text,
            title = model.title,
            userInfo = userInfoRemoteMapper.mapFromModel(model.userInfo)
        )
    }
}
