package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.arabam.android.assigment.base.BaseViewModel
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.data.repository.AdvertRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repo:AdvertRepo
):BaseViewModel() {


    private lateinit var _adverts: Flow<PagingData<ListingAdvert>>

    val adverts get() = _adverts

    init {
        getAdverts()
    }


    private fun getAdverts(){
        launchPagingAsync({
            repo.allAdverts(null,null,null,null)
        },{
            _adverts = it
        })
    }


}