package com.arabam.android.assignment.feature.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assignment.core.data.repo.abstraction.DbRepo
import com.arabam.android.assignment.core.model.ListingAdvert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesFragmentViewModel @Inject constructor(
    private val repo: DbRepo,
) : ViewModel() {

    private val _favorites = MutableLiveData<List<ListingAdvert>>()

    val favorites: LiveData<List<ListingAdvert>> get() = _favorites

    init {
        favorites()
    }

    private fun favorites() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.favorites()
                .onEach {
                    _favorites.value = it
                }
                .launchIn(viewModelScope)
        }
    }

    fun removeFromFav(advert: ListingAdvert) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.removeFromFav(advert)
        }
    }
}
