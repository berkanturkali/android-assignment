package com.arabam.android.assignment.feature.details.viewmodel

import androidx.lifecycle.*
import com.arabam.android.assignment.core.common.utils.Constants.ADVERT_ID
import com.arabam.android.assignment.core.data.repo.abstraction.AdvertRepo
import com.arabam.android.assignment.core.data.repo.abstraction.DbRepo
import com.arabam.android.assignment.core.model.Event
import com.arabam.android.assignment.core.model.DetailAdvert
import com.arabam.android.assignment.core.model.ListingAdvert
import com.arabam.android.assignment.core.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _showLastVisitedItems = MutableLiveData<Event<Boolean>>()

    val showLastVisitedItems: LiveData<Event<Boolean>> get() = _showLastVisitedItems

    private lateinit var listingAdvert: ListingAdvert

    val lastVisitedItems: LiveData<List<ListingAdvert>> get() = _lastVisitedItems

    init {
        savedStateHandle.get<Int>(ADVERT_ID)?.let {
            getAdvert(it)
            isAdvertInFavorites(it)
        }
        getLastVisitedItems()
    }

    private val _isAdvertInDb = MutableLiveData<Event<Boolean>>()

    val isAdvertInDb: LiveData<Event<Boolean>> get() = _isAdvertInDb

    private val _setFav = MutableStateFlow<Boolean?>(null)

    val setFav: StateFlow<Boolean?> get() = _setFav

    private fun getAdvert(id: Int) {
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

    private fun isAdvertInFavorites(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _isAdvertInDb.value =
                Event(withContext(Dispatchers.IO) { dbRepo.getAdvert(id) != null })
        }
    }

    private fun getLastVisitedItems() {
        viewModelScope.launch(Dispatchers.Main) {
            _lastVisitedItems.value = dbRepo.getLastVisitedItems()
            _showLastVisitedItems.value = Event(!_lastVisitedItems.value.isNullOrEmpty())

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
