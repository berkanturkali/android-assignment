package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.arabam.android.assigment.base.BaseViewModel
import com.arabam.android.assigment.data.model.sort.SortItem
import com.arabam.android.assigment.data.model.year.YearItem
import com.arabam.android.assigment.data.repository.AdvertRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
) : BaseViewModel() {

    private val sortOrder = MutableStateFlow<SortItem>(SortItem(""))

    private val year = MutableStateFlow(YearItem())

    private val _adverts = combine(
        sortOrder,
        year
    ) { order, year ->
        Pair(order, year)
    }.flatMapLatest {
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
}