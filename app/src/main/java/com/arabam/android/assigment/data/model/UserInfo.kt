package com.arabam.android.assigment.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val id: Int,
    val nameSurname: String,
    val phone: String,
    val phoneFormatted: String
): Parcelable