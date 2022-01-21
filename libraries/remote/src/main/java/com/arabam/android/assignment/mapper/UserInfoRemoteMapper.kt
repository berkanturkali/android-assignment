package com.arabam.android.assignment.mapper

import com.arabam.android.assignment.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.model.UserInfo
import com.arabam.android.assignment.model.UserInfoDto
import javax.inject.Inject

class UserInfoRemoteMapper @Inject constructor() : RemoteModelMapper<UserInfoDto, UserInfo> {
    override fun mapFromModel(model: UserInfoDto): UserInfo {
        return UserInfo(
            id = model.id,
            nameSurname = model.nameSurname,
            phone = model.phone,
            phoneFormatted = model.phoneFormatted
        )
    }
}
