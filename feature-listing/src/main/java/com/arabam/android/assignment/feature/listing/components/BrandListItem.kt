package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Badge
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.feature.listing.model.category.CategoryKey

@Composable
fun BrandListItem(
    item: CategoryKey?,
    modelCount: Int,
    showBadge: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        item?.let {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item.logo?.let {
                    if (it != 0) {
                        Image(
                            painter = painterResource(id = item.logo),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }

                item.title?.let {
                    Text(
                        text = String.format(
                            stringResource(id = R.string.brand_and_model_count),
                            item.title,
                            modelCount
                        ),
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.primary_text_color)
                    )
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                if (showBadge) {
                    Badge(
                        backgroundColor = colorResource(id = R.color.primary_color),
                        modifier = Modifier.padding(top = 4.dp, end = 4.dp)
                    ) {
                        Text(
                            text = "1",
                            style = MaterialTheme.typography.body2,
                            color = colorResource(id = R.color.on_primary)
                        )
                    }
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_down),
                    contentDescription = null,
                    tint = colorResource(
                        id = R.color.light_gray
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BrandListItemPrev() {
    MaterialTheme {
        BrandListItem(
            item = CategoryKey(title = "Brand"),
            modelCount = 3,
            onItemClick = { },
            showBadge = true
        )
    }
}