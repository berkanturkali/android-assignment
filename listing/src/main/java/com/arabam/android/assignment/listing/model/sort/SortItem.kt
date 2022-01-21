package com.arabam.android.assignment.listing.model.sort

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SortItem(
    var title: String? = null,
    var type: SortTypes? = null,
    var direction: SortDirections? = null,
    var isSelected: Boolean = false,
) : Parcelable
