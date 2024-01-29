package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.R.color

@Composable
fun MenuButton(
    iconRotationDegree: Float,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonSize: Dp = 32.dp
) {
    Button(
        onClick = {
            onButtonClick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(
                color.primary_color,
            ),
            contentColor = colorResource(color.on_primary)
        ),
        modifier = modifier
            .clip(CircleShape)
            .size(buttonSize),
        contentPadding = PaddingValues(0.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier
                .rotate(iconRotationDegree)
                .size(buttonSize / 2f)

        )
    }
}

@Preview
@Composable
fun MenuButtonPrev() {
    MenuButton(0f,{})
}