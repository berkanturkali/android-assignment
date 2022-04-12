package com.arabam.android.assignment.database.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arabam.android.assignment.remote.model.Location

@Entity
public data class AdvertEntity(
    @PrimaryKey
    val id: Int,
    @Embedded
    val location: Location,
    var photo: String,
    val price: Int,
    val title: String,
)
