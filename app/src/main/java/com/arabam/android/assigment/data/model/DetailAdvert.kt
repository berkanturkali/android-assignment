package com.arabam.android.assigment.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailAdvert(
    val category: Category,
    val date: String,
    val dateFormatted: String,
    val id: Int,
    val location: Location,
    val modelName: String,
    val photos: List<String>,
    val price: Int,
    val priceFormatted: String,
    val properties: List<Property>,
    val text: String,
    val title: String,
    val userInfo: UserInfo
): Parcelable