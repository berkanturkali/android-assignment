package com.arabam.android.assignment.feature.details.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.core.model.Command

data class Option(
    @DrawableRes var icon: Int,
    val command: Command? = null,
    @ColorRes var tint: Int = color.on_primary,
    val type: OptionType,
) {
    fun action() {
        command?.execute()
    }
}