package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Slider(
    initialPage: Int,
    images: List<String>,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        AdvertImagePager(
            pageCount = images.size,
            images = images,
            onImageClick = { _, _ -> },
            initialPage = initialPage
        )

    }
}