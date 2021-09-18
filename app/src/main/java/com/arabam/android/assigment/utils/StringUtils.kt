package com.arabam.android.assigment.utils

import java.text.DecimalFormat

fun Int.formatPrice(): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(this).replace(",",".")
}

fun String.resize():String{
    return this.replace("{0}","160x120")
}