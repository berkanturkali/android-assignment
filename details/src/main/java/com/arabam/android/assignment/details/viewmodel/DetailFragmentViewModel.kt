package com.arabam.android.assignment.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assignment.detail.DetailAdvert
import com.arabam.android.assignment.listing.model.ListingAdvert
import com.arabam.android.assignment.listing.model.Resource
import com.arabam.android.assignment.repo.AdvertRepo
import com.arabam.android.assignment.repo.DbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
    private val dbRepo: DbRepo,
) : ViewModel() {

    private val _advert = MutableLiveData<Resource<DetailAdvert>>()
    val advert: LiveData<Resource<DetailAdvert>> get() = _advert

    private val _isAdvertInDb = Channel<Boolean>()

    val isAdvertInDb get() = _isAdvertInDb.receiveAsFlow()

    fun getAdvert(id: Int) {
        _advert.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.Main) {
            _advert.value = repo.advert(id)
        }
    }

    fun addToFav(advert: ListingAdvert) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.addToFav(advert)
        }
    }

    fun removeFromFav(advert: ListingAdvert) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.removeFromFav(advert)
        }
    }

    fun isAdvertInFavorites(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _isAdvertInDb.send(withContext(Dispatchers.IO) { dbRepo.getAdvert(id) != null })
        }
    }
}