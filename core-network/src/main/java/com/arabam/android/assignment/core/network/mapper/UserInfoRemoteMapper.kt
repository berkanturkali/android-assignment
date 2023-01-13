package com.arabam.android.assignment.core.network.mapper

import com.arabam.android.assignment.core.model.UserInfo
import com.arabam.android.assignment.core.network.mapper.base.RemoteModelMapper
import com.arabam.android.assignment.core.network.model.UserInfoDto
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
