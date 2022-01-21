package com.arabam.android.assignment.listing.ui.category

import androidx.lifecycle.ViewModel
import com.arabam.android.assignment.listing.model.category.CategoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryContainerFragmentViewModel : ViewModel() {

    private val _selectedCategoryItem = MutableStateFlow(CategoryItem(-1))

    val selectedCategoryItem: StateFlow<CategoryItem> get() = _selectedCategoryItem

    fun setSelectedCategoryId(categoryItem: CategoryItem) {
        _selectedCategoryItem.value = categoryItem
    }
}
