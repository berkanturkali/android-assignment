package com.arabam.android.assigment.data.model.year

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class YearItem(
    val minYear:Int?= null,
    val maxYear:Int? = null
):Parcelable