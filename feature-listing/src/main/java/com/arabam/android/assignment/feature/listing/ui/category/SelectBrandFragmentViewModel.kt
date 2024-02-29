package com.arabam.android.assignment.feature.listing.ui.category

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assignment.core.common.utils.Constants
import com.arabam.android.assignment.feature.listing.R
import com.arabam.android.assignment.feature.listing.model.category.CategoryItem
import com.arabam.android.assignment.feature.listing.model.category.CategoryKey
import com.arabam.android.assignment.feature.listing.usecase.GenerateTitleFromModelNameUseCase
import com.arabam.android.assignment.feature.listing.usecase.GetLogoByTheKeyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SelectBrandFragmentViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getLogoByTheKeyUseCase: GetLogoByTheKeyUseCase,
    private val generateTitleFromModelName: GenerateTitleFromModelNameUseCase,
    private val savedStateHandle: SavedStateHandle,
) :
    ViewModel() {

    private val _categories = MutableStateFlow<Map<CategoryKey?, List<CategoryItem>>?>(null)
    val categories get() = _categories.asStateFlow()

    var showBadge: Boolean = false

    var categoryId: Int? = null

    init {
        getBrands(context)
        showBadge =
            savedStateHandle.get<Boolean>(Constants.SHOW_BADGE_ON_SELECT_BRAND_FRAGMENT) ?: false
        categoryId = savedStateHandle.get<Int>(Constants.SELECTED_CATEGORY)
    }

    private fun getBrands(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val categories = getCategories(context)
            val brands = generateBrandList(categories, context)
            _categories.value = brands
        }
    }

    private fun getCategories(context: Context): List<CategoryItem> {
        val ids = context.resources.getStringArray(R.array.model_ids)
        val names = context.resources.getStringArray(R.array.model_names)
        val categories = mutableListOf<CategoryItem>()
        for (i in ids.indices) {
            val title = generateTitleFromModelName(names[i])
            val category = CategoryItem(
                id = ids[i].toInt(),
                title = title,
                name = names[i]
            )
            categories.add(category)
        }
        return categories
    }


    private suspend fun generateBrandList(
        list: List<CategoryItem>,
        context: Context
    ): Map<CategoryKey?, List<CategoryItem>> =
        withContext(Dispatchers.Default) {
            list.sortedWith(compareBy(CategoryItem::title))
                .groupBy(CategoryItem::title)
                .mapKeys {
                    it.key?.let { key ->
                        val logo = getLogoByTheKeyUseCase(key, context)
                        CategoryKey(
                            title = it.key,
                            logo = logo
                        )
                    }
                }
        }

}
