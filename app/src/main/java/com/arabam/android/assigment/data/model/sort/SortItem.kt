package com.arabam.android.assigment.data.model.sort

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SortItem(
    var title:String,
    var type:SortTypes?,
    var direction:SortDirections?,
    var isSelected:Boolean = false
):Parcelable