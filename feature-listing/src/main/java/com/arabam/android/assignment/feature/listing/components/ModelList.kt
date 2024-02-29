package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.feature.listing.model.category.CategoryItem

@Composable
fun ModelList(
    modelList: List<CategoryItem>,
    onItemClick: (CategoryItem) -> Unit,
    selectedItem: CategoryItem,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        LazyColumn {
            itemsIndexed(modelList) { index, item ->
                item.name?.let {
                    ModelListItem(
                        model = item,
                        onItemClick = { item ->
                            onItemClick(item)
                        },
                        isChecked = item.id == selectedItem.id,
                        preSelectedItem = selectedItem
                    )

                    if (index != modelList.lastIndex) {
                        Divider(thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModelListPrev() {
    ModelList(
        modelList = (0..10).map { CategoryItem(name = it.toString()) },
        onItemClick = {},
        selectedItem = CategoryItem()
    )
}