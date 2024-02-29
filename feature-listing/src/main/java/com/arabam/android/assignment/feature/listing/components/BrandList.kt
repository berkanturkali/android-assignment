package com.arabam.android.assignment.feature.listing.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arabam.android.assignment.feature.listing.model.category.CategoryItem
import com.arabam.android.assignment.feature.listing.model.category.CategoryKey

@Composable
fun BrandList(
    list: Map<CategoryKey?, List<CategoryItem>>?,
    categoryId: Int,
    showBadge: Boolean,
    onCategoryClick: (CategoryKey, List<CategoryItem>) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        list?.keys?.toTypedArray()?.let { categoryList ->
            itemsIndexed(categoryList) { index, item ->
                val modelList = list.values.toTypedArray()[index]
                BrandListItem(
                    item = item,
                    modelCount = list.values.toTypedArray()[index].size,
                    onItemClick = {
                        onCategoryClick(item!!, modelList)
                    },
                    showBadge = modelList.firstOrNull { it.id == categoryId } != null && showBadge
                )
                if (index != categoryList.lastIndex) {
                    Divider(thickness = 0.5.dp, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }

}