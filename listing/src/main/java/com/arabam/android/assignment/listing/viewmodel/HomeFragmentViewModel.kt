package com.arabam.android.assignment.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arabam.android.assignment.listing.model.category.CategoryItem
import com.arabam.android.assignment.listing.model.sort.SortDirections
import com.arabam.android.assignment.listing.model.sort.SortItem
import com.arabam.android.assignment.listing.model.sort.SortTypes
import com.arabam.android.assignment.listing.model.year.YearItem
import com.arabam.android.assignment.repo.AdvertRepo
import com.example.core.storage.HomeScreenPreferences
import com.example.core.storage.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
    private val manager: PreferencesManager,
) : ViewModel() {

    private lateinit var sortItem: SortItem

    private var categoryId: Int? = null

    private val _shouldScrollToTop = MutableStateFlow<Boolean>(false)

    val shouldScrollToTop: StateFlow<Boolean> get() = _shouldScrollToTop

    private lateinit var year: YearItem
    val viewPreferencesFlow =
        manager.viewPreferencesFlow

    private val _adverts = manager.filterPreferences
        .map { preferences ->
            categoryId = preferences.category
            sortItem =
                SortItem(type = SortTypes.values().firstOrNull { it.value == preferences.sortType },
                    direction = SortDirections.values()
                        .firstOrNull { preferences.sortDirection == it.value })
            year = YearItem(preferences.minYear, preferences.maxYear)
            preferences
        }
        .flatMapLatest {
            repo.allAdverts(
                it.category,
                it.sortType,
                it.sortDirection,
                it.minYear,
                it.maxYear)
        }

    val adverts = _adverts.asLiveData().cachedIn(viewModelScope)


    fun updateSortOrder(item: SortItem) {
        _shouldScrollToTop.value = true
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateSortPreferences(item.direction?.value, item.type?.value)
        }
    }

    fun updateYear(year: YearItem) {
        _shouldScrollToTop.value = true
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateMinMaxYear(year.minYear, year.maxYear)
        }
    }

    fun updateCategory(item: CategoryItem) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateCategory(item.id)
        }
    }

    fun getSortItem() = sortItem

    fun getYearItem() = year

    fun getCategory() = categoryId

    fun setGridMode(isGridMode: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            manager.updateViewPreferences(HomeScreenPreferences.ViewPreferences(isGridMode))
        }
    }
}