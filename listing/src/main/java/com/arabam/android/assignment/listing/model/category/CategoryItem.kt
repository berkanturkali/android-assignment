package com.arabam.android.assignment.listing.model.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryItem(
    val id: Int? = null,
    val title: String? = null,
    val name: String? = null,
    var checked: Boolean = false
) : Parcelable
