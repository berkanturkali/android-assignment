package com.arabam.android.assignment.listing.ui.category

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assignment.listing.R
import com.arabam.android.assignment.listing.model.category.CategoryItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SelectBrandFragmentViewModel @Inject constructor(@ApplicationContext context: Context) :
    ViewModel() {

    private val _categories = MutableStateFlow<Map<String?, List<CategoryItem>>?>(null)
    val categories: MutableStateFlow<Map<String?, List<CategoryItem>>?> get() = _categories

    init {
        getBrands(context)
    }

    private fun getBrands(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val brands = mapList(getCategories(context))
            _categories.value = brands
        }
    }
}

private fun getCategories(context: Context): List<CategoryItem> {
    val ids = context.resources.getStringArray(R.array.model_ids)
    val names = context.resources.getStringArray(R.array.model_names)
    val categories = mutableListOf<CategoryItem>()
    for (i in ids.indices) {
        val category = CategoryItem(
            id = ids[i].toInt(),
            title = names[i].generateTitle(),
            name = names[i]
        )
        categories.add(category)
    }
    return categories
}

private suspend fun mapList(list: List<CategoryItem>): Map<String?, List<CategoryItem>> =
    withContext(Dispatchers.Default) {
        list.sortedWith(compareBy(CategoryItem::title))
            .groupBy(CategoryItem::title)
    }

private fun String.generateTitle(): String {
    return this.split("-")[0].replaceFirstChar { it.titlecase(Locale.getDefault()) }
}
