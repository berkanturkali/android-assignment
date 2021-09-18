package com.arabam.android.assigment.db.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arabam.android.assigment.data.model.Location

@Entity
data class AdvertEntity(
    val date: String,
    val dateFormatted: String,
    @PrimaryKey
    val id: Int,
    @Embedded
    val location: Location,
    val modelName: String,
    var photo: String,
    val price: Int,
    val priceFormatted: String,
    val title: String,
)