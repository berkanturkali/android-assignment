package com.arabam.android.assignment.common.bindings

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.arabam.android.assignment.common.R

@BindingAdapter(value = ["setBg"])
fun ConstraintLayout.setBg(
    position: Int
) {
    val bgColor = if (position % 2 == 0)R.color.xx_light_gray else R.color.white
    setBackgroundColor(ContextCompat.getColor(context, bgColor))
}
