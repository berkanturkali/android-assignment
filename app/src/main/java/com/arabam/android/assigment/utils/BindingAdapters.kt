package com.arabam.android.assigment.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assigment.R
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun ImageView.load(
    url: String?,
) {
    url?.let {
        Glide.with(this).load(url)
            .error(R.drawable.ic_camera).into(this)
    }
}