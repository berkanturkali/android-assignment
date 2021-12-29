package com.arabam.android.assignment.details.model.info

import android.icu.text.IDNA
import android.os.Parcelable
import com.arabam.android.assignment.details.R
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
