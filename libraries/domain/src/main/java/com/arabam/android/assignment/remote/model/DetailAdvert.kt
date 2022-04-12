package com.arabam.android.assignment.remote.model

public data class DetailAdvert(
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
