package com.arabam.android.assignment.core.network.model

data class ListingAdvertDto(
    val category: CategoryDto,
    val date: String,
    val dateFormatted: String,
    val id: Int,
    val location: LocationDto,
    val modelName: String,
    var photo: String,
    val price: Int,
    val priceFormatted: String,
    val properties: List<PropertyDto>,
    val title: String,
)
