package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.feature.listing.model.sort.SortItem

@Composable
fun SortList(
    list: List<SortItem>,
    onClearTextClick: () -> Unit,
    onItemClick: (SortItem) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(modifier = modifier) {

        item {
            SortListTopBar(
                onClearTextClick = onClearTextClick,
                clearTextVisible = list.any { it.isSelected })
            Divider(thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
        }
        itemsIndexed(list) { index, item ->
            SortListItem(item = item, showCheckmark = item.isSelected, onItemClick = onItemClick)
            if (index != list.lastIndex) {
                Divider(thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }

}

@Preview
@Composable
fun SortListPrev() {
    SortList(list = emptyList(), onClearTextClick = { /*TODO*/ }, onItemClick = {})
}