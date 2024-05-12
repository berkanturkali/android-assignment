package com.arabam.android.assignment.feature.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.feature.details.model.info.Info

@Composable
fun AdvertInfo(
    infoList: List<Info>,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        infoList.fastForEachIndexed { index, info ->
            when (info) {
                is Info.InfoWithText -> {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 0.2.dp,
                                color = colorResource(id = R.color.divider_color).copy(alpha = 0.2f)
                            )
                            .padding(8.dp),
                        text = info.text,
                        style = MaterialTheme.typography.body2,
                        color = colorResource(id = info.color),
                        textAlign = TextAlign.Center
                    )
                }

                is Info.InfoWithTitle -> {
                    val background = if (index % 2 == 0) {
                        R.color.xx_light_gray
                    } else {
                        R.color.white
                    }
                    InfoWithTitleItem(
                        title = info.title,
                        content = info.text,
                        color = info.color,
                        backgroundColor = background
                    )
                }
            }
        }
    }
}

@Composable
fun InfoWithTitleItem(
    title: String,
    content: String,
    color: Int,
    backgroundColor: Int,
    modifier: Modifier = Modifier
) {

    val paddingModifier =
        Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 0.2.dp,
                color = colorResource(id = R.color.divider_color).copy(alpha = 0.2f)
            )
            .background(colorResource(id = backgroundColor)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.primary_text_color),
            modifier = paddingModifier
        )

        Text(
            text = content,
            style = MaterialTheme.typography.body2,
            color = colorResource(id = color),
            modifier = paddingModifier
        )
    }
}

@Preview
@Composable
private fun InfoWithTitleItemPrev() {
    MaterialTheme {
        InfoWithTitleItem(
            title = "Title",
            content = "Content",
            color = R.color.tertiary_color,
            backgroundColor = R.color.xx_light_gray
        )
    }
}