package com.arabam.android.assignment.details

import android.widget.ImageView

interface ImageClickListener {
    fun onImageClick(images:List<String>,position:Int)
}