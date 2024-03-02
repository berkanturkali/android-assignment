package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Slider(
    initialPage: Int,
    images: List<String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        AdvertImagePager(
            childModifier = Modifier
                .fillMaxSize(),
            pageCount = images.size,
            images = images,
            onImageClick = { _, _ -> },
            initialPage = initialPage
        )
    }
}