package com.arabam.android.assignment.feature.details.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.arabam.android.assignment.feature.details.components.AdvertImagePager

@Composable
fun DetailScreenContent(
    images: List<String>,
    modifier: Modifier = Modifier
) {

    var showSlider by rememberSaveable {
        mutableStateOf(false)
    }

    var imagePosition by rememberSaveable {
        mutableStateOf(0)
    }

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (imagePager, tabLayout) = createRefs()

        AdvertImagePager(
            modifier = Modifier
                .height(200.dp)
                .constrainAs(imagePager) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            images = images,
            pageCount = images.size,
            onImageClick = { _, position ->
                imagePosition = position
                showSlider = true
            })


    }

}