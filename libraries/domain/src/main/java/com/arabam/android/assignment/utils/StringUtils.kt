package com.arabam.android.assignment.utils

import java.text.DecimalFormat

fun Int.formatPrice(): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(this).replace(",", ".")
}

fun String.resize(size: String = "800x600"): String {
    return this.replace("{0}", size)
}
