package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuItemLabel(
    label: String,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = colorResource(id = com.arabam.android.assignment.core.common.R.color.primary_color).copy(
            alpha = 0.7f
        ),
        contentColor = colorResource(id = com.arabam.android.assignment.core.common.R.color.on_primary)
    ) {

        Text(
            text = label,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 24.dp)
        )

    }

}

@Preview
@Composable
fun MenuItemLabelPrev() {
    MenuItemLabel(label = "Label")
}