package com.arabam.android.assignment.feature.details.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assignment.core.common.utils.Constants.ADVERT_ID
import com.arabam.android.assignment.core.data.repo.abstraction.AdvertRepo
import com.arabam.android.assignment.core.data.repo.abstraction.DbRepo
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.model.Resource
import com.arabam.android.assignment.feature.details.model.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
    private val dbRepo: DbRepo,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _advert = MutableLiveData<Resource<DetailAdvert>>()
    val advert: LiveData<Resource<DetailAdvert>> get() = _advert

    private val _lastVisitedItems = MutableLiveData<List<ListingAdvert>>()

    private lateinit var listingAdvert: ListingAdvert

    val lastVisitedItems: LiveData<List<ListingAdvert>> get() = _lastVisitedItems


    private val _setFav = MutableStateFlow(false)

    val setFav get() = _setFav.asStateFlow()


    var optionList by mutableStateOf(mutableListOf<Option>())

    var id: Int? = null

    init {
        savedStateHandle.get<Int>(ADVERT_ID)?.let {
            id = it
            getAdvert(it)
            checkTheAdvertInFavorites(it)
        }
        getLastVisitedItems()
    }

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

    private fun checkTheAdvertInFavorites(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _setFav.value =
                withContext(Dispatchers.IO) { dbRepo.getAdvert(id) != null }
        }
    }

    private fun getLastVisitedItems() {
        viewModelScope.launch(Dispatchers.Main) {
            _lastVisitedItems.value = dbRepo.getLastVisitedItems()
        }
    }

    fun initAdvert(advert: ListingAdvert) {
        if (!::listingAdvert.isInitialized) {
            this.listingAdvert = advert
            insertIntoLastVisitedItems(this.listingAdvert)
        }
    }

    fun getAdvert() = listingAdvert

    fun setFav(fav: Boolean) {
        _setFav.value = fav
    }

    private fun insertIntoLastVisitedItems(advert: ListingAdvert) {
        viewModelScope.launch(Dispatchers.IO) {
            dbRepo.insertToLastVisited(advert)
        }
    }
}
