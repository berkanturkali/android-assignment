package com.arabam.android.assignment.listing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arabam.android.assignment.listing.model.sort.SortItem
import com.arabam.android.assignment.listing.model.year.YearItem
import com.arabam.android.assignment.repo.AdvertRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
) : ViewModel() {

    private val sortOrder = MutableStateFlow<SortItem>(SortItem(""))

    private val year = MutableStateFlow(YearItem())

    private val _isGridMode = MutableStateFlow(false)

    val isGridMode: StateFlow<Boolean> get() = _isGridMode

    private val _adverts = combine(
        sortOrder,
        year,
        ::Pair
    ).flatMapLatest {
        repo.allAdverts(it.first.type?.value,
            it.first.direction?.value,
            it.second.minYear,
            it.second.maxYear)
    }

    val adverts = _adverts.asLiveData().cachedIn(viewModelScope)


    fun updateSortOrder(item: SortItem) {
        sortOrder.value = item
    }

    fun updateYear(year: YearItem) {
        this.year.value = year
    }

    fun getSortOrder() = sortOrder.value

    fun setGridMode(isGridMode: Boolean) {
        _isGridMode.value = isGridMode
    }
}