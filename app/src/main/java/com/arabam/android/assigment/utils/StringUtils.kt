package com.arabam.android.assigment.utils

import java.text.DecimalFormat

fun Int.formatPrice(): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(this).replace(",",".")
}

fun String.resize(size:String = "160x120"):String{
    return this.replace("{0}",size)
}