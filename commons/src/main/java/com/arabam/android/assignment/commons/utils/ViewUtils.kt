package com.arabam.android.assignment.commons.utils

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.arabam.android.assignment.commons.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnack(message: String, color: Int = R.color.colorError) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(ContextCompat.getColor(this.requireContext(), color))
        .show()
}