package com.arabam.android.assignment.commons.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arabam.android.assignment.commons.R
import com.bumptech.glide.Glide

@BindingAdapter(value = ["url", "circularProgressDrawable"], requireAll = false)
fun ImageView.load(
    url: String?,
    circularProgressDrawable: CircularProgressDrawable,
) {
    url?.let {
        Glide.with(this).load(url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_camera).into(this)
    }
}
