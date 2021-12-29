package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.listing.model.UserInfoDto
import javax.inject.Inject

class UserInfoRemoteMapper @Inject constructor():RemoteModelMapper<UserInfoDto, com.arabam.android.assignment.listing.model.UserInfo>{
    override fun mapFromModel(model: UserInfoDto): com.arabam.android.assignment.listing.model.UserInfo {
        return com.arabam.android.assignment.listing.model.UserInfo(
            id = model.id,
            nameSurname = model.nameSurname,
            phone = model.phone,
            phoneFormatted = model.phoneFormatted
        )
    }
}