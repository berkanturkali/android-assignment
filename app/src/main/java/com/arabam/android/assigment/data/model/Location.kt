package com.arabam.android.assigment.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val cityName: String,
    val townName: String
):Parcelable