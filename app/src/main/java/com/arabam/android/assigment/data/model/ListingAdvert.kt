package com.arabam.android.assigment.data.model

import android.location.Location
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListingAdvert(
    val category: Category,
    val date: String,
    val dateFormatted: String,
    val id: Int,
    val location: Location,
    val modelName: String,
    var photo: String,
    val price: Int,
    val priceFormatted: String,
    val properties: List<Property>,
    val title: String
):Parcelable