package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.utils.shimmerModifier

@Composable
fun DetailsLoadingView(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }

        items(5) {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }

        item {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    repeat(3) {
                        ShimmerItem(
                            modifier = Modifier
                                .size(80.dp),
                            shape = CircleShape
                        )
                    }
                }
            }
        }


        item {
            ShimmerItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
        }

        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                items(5) {
                    ShimmerItem(
                        modifier = Modifier
                            .width(300.dp)
                            .height(200.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ShimmerItem(modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(16.dp)) {
    Box(
        modifier = modifier
            .shimmerModifier(shape = shape)
    )
}

@Preview
@Composable
fun DetailsLoadingViewPrev() {
    DetailsLoadingView()
}