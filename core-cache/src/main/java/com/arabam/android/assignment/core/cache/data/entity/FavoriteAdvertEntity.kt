package com.arabam.android.assignment.core.cache.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arabam.android.assignment.core.model.Location

@Entity
data class FavoriteAdvertEntity(
    @PrimaryKey
    val id: Int,
    @Embedded
    val location: Location,
    var photo: String,
    val price: Int,
    val title: String,
)
