package com.arabam.android.assigment.data

import android.widget.ImageView

interface ImageClickListener{
    fun onImageClick(imageView: ImageView, url:String)
}