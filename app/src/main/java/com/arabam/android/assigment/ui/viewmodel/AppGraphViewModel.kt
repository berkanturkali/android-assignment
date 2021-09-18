package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assigment.base.BaseViewModel
import com.arabam.android.assigment.data.model.ListingAdvert
import com.arabam.android.assigment.data.repository.DbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppGraphViewModel @Inject constructor(
    private val repo:DbRepo
):ViewModel() {

    suspend fun favorites(): Flow<List<ListingAdvert>> = repo.favorites()

    fun addToFav(advert: ListingAdvert) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addToFav(advert)
        }
    }

    fun removeFromFav(advert: ListingAdvert) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.removeFromFav(advert)
        }
    }

    suspend fun getAdvert(id: Int) = repo.getAdvert(id)

}