package com.arabam.android.assignment.remote.mapper

import com.arabam.android.assignment.remote.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.remote.model.UserInfo
import com.arabam.android.assignment.remote.model.UserInfoDto
import javax.inject.Inject

public class UserInfoRemoteMapper @Inject constructor() : RemoteModelMapper<UserInfoDto, UserInfo> {
    override fun mapFromModel(model: UserInfoDto): UserInfo {
        return UserInfo(
            id = model.id,
            nameSurname = model.nameSurname,
            phone = model.phone,
            phoneFormatted = model.phoneFormatted
        )
    }
}
