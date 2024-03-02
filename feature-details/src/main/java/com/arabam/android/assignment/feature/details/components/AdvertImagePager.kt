package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.utils.resize

@Composable
fun AdvertImagePager(
    pageCount: Int,
    images: List<String>,
    onImageClick: (String, Int) -> Unit,
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    initialPage: Int = 0,
) {
    val pagerState = rememberPagerState(pageCount = { pageCount }, initialPage = initialPage)

    HorizontalPager(state = pagerState, modifier = modifier) { page ->
        val url = images[page].resize()
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current).data(url)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build()
        )

        if (painter.state is AsyncImagePainter.State.Loading) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.primary_color),
                    modifier = Modifier
                        .width(32.dp)
                )
            }

        } else {
            Image(
                painter = painter,
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = childModifier
                    .fillMaxWidth()
                    .clickable {
                        onImageClick(images[page], page)
                    }
            )
        }

    }
}