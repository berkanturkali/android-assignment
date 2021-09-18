package com.arabam.android.assigment.ui.viewmodel

import androidx.paging.PagingData
import com.arabam.android.assigment.base.BaseViewModel
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.data.model.sort.SortItem
import com.arabam.android.assigment.data.repository.AdvertRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
) : BaseViewModel() {

    private val sortOrder = MutableStateFlow<SortItem>(SortItem(""))

    private lateinit var _adverts: Flow<PagingData<ListingAdvert>>

    val adverts get() = _adverts

    init {
        getAdverts()
    }

    private fun getAdverts() {
        launchPagingAsync({
            repo.allAdverts(null, null, null, null)
        }, {
            _adverts = it
        })
    }

    fun updateSortOrder(item: SortItem) {
        sortOrder.value = item
    }

    fun getSortOrder() = sortOrder.value
}