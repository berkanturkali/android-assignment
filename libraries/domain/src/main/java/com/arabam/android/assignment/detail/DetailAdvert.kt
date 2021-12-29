package com.arabam.android.assignment.detail

import com.arabam.android.assignment.listing.model.Category
import com.arabam.android.assignment.listing.model.Location
import com.arabam.android.assignment.listing.model.Property
import com.arabam.android.assignment.listing.model.UserInfo


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
)