package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R

@Composable
fun AdvertDescription(
    description: String,
    modifier: Modifier = Modifier
) {

    Text(
        modifier = modifier.padding(16.dp),
        text = description,
        style = MaterialTheme.typography.body1,
        color = colorResource(id = R.color.primary_text_color)
    )
}

@Preview(showBackground = true)
@Composable
fun AdvertDescriptionPrev(@PreviewParameter(LoremIpsum::class) text: String) {
    MaterialTheme {
        AdvertDescription(description = text)
    }
}