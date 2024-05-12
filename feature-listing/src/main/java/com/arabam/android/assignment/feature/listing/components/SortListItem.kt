package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.feature.listing.model.sort.SortItem

@Composable
fun SortListItem(
    item: SortItem,
    showCheckmark: Boolean,
    onItemClick: (SortItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(item)
            }
            .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = String.format(
                stringResource(id = R.string.sort_text), item.title!!, item.direction?.text
            ),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.primary_text_color)
        )

        if (showCheckmark) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = colorResource(
                    id = R.color.checkmark_color
                ),
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SortListItemPrev() {
    SortListItem(item = SortItem("Title"), showCheckmark = true, onItemClick = {})
}