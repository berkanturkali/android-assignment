package com.arabam.android.assignment.domain.utils

import java.text.DecimalFormat

public fun Int.formatPrice(): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(this).replace(",", ".")
}

public fun String.resize(size: String = "800x600"): String {
    return this.replace("{0}", size)
}
