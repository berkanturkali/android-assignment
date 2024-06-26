package com.arabam.android.assignment.core.network.model

import com.arabam.android.assignment.core.model.Location

public data class DetailAdvertDto(
    val category: CategoryDto,
    val date: String,
    val dateFormatted: String,
    val id: Int,
    val location: Location,
    val modelName: String,
    val photos: List<String>,
    val price: Int,
    val priceFormatted: String,
    val properties: List<PropertyDto>,
    val text: String,
    val title: String,
    val userInfo: UserInfoDto
)
