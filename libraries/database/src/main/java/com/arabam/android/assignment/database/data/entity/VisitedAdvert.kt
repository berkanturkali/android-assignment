package com.arabam.android.assignment.database.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arabam.android.assignment.model.Location

@Entity(tableName = "last_visited_adverts")
data class VisitedAdvert(
    @PrimaryKey
    val id: Int,
    @Embedded
    val location: Location,
    var photo: String,
    val price: Int,
    val model: String,
    val visitedAt: Long = System.currentTimeMillis()
)
