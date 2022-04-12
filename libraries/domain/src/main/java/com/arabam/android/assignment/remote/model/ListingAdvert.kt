package com.arabam.android.assignment.remote.model

public data class ListingAdvert(
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
    val title: String,
)
