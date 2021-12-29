package com.arabam.android.assignment.details

import android.widget.ImageView

interface ImageClickListener {
    fun onImageClick(imageView: ImageView, url: String)
}