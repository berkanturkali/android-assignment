package com.arabam.android.assignment.feature.details.model.info

import android.os.Parcelable
import com.arabam.android.assignment.core.common.R

import kotlinx.parcelize.Parcelize

sealed class Info : Parcelable {
    @Parcelize
    data class InfoWithTitle(
        val title: String,
        val text: String,
        val color: Int = R.color.black,
    ) : Info()

    @Parcelize
    data class InfoWithText(
        val text: String,
        val color: Int = R.color.black,
    ) : Info()
}
