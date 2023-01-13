package com.arabam.android.assignment.feature.details

import android.view.View

interface ImageClickListener {
    fun onImageClick(images: List<String>, position: Int, view: View)
}
