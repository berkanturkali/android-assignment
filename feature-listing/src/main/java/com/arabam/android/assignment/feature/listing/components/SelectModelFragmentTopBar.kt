package com.arabam.android.assignment.feature.listing.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arabam.android.assignment.core.common.R.color
import com.arabam.android.assignment.core.common.R.drawable

@Composable
fun SelectModelFragmentTopBar(
    @DrawableRes logoRes: Int,
    onCloseButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        val (logo, exitButton) = createRefs()

        Image(
            painter = painterResource(id = logoRes),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
                .constrainAs(logo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
        )

        Icon(
            painter = painterResource(id = drawable.ic_close),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(exitButton) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .clickable {
                    onCloseButtonClick()
                }
                .padding(16.dp),
            tint = colorResource(id = color.primary_text_color)
        )

    }
}

@Preview
@Composable
fun SelectModelFragmentTopBarPrev() {
    SelectModelFragmentTopBar(logoRes = drawable.chery_logo, {})
}