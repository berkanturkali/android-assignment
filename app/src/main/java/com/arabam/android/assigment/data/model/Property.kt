package com.arabam.android.assigment.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Property(
    val name: String,
    val value: String
):Parcelable