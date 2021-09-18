package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assigment.utils.ClickEvent
import com.arabam.android.assigment.utils.ShowEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _showProgress = Channel<ShowEvent.ShowProgressEvent>()

    val showProgress get() = _showProgress.receiveAsFlow()

    private val _sortClick = Channel<ClickEvent.SortItemClick>()

    val sortClick get() = _sortClick.receiveAsFlow()

    private val _yearFilterClick = Channel<ClickEvent.YearItemClick>()

    val yearFilterClick = _yearFilterClick.receiveAsFlow()

    private val _isFloatingVisible = Channel<Boolean>()

    val isFloatingVisible get() = _isFloatingVisible.receiveAsFlow()

    private val _isAdvertInFavorites = Channel<Boolean>()

    val isAdvertInFavorites get() = _isAdvertInFavorites.receiveAsFlow()

    private val _isFavChecked = Channel<Boolean>()

    val isFavChecked get() = _isFavChecked.receiveAsFlow()

    fun showProgress(show: Boolean) {
        viewModelScope.launch {
            _showProgress.send(ShowEvent.ShowProgressEvent(show))
        }
    }

    fun setSortClick(isClick: Boolean) {
        viewModelScope.launch {
            _sortClick.send(ClickEvent.SortItemClick(isClick))
        }
    }

    fun setYearFilterClick(isClick: Boolean) {
        viewModelScope.launch {
            _yearFilterClick.send(ClickEvent.YearItemClick(isClick))
        }
    }

    fun setFloatingVisibility(isVisible:Boolean){
        viewModelScope.launch {
            _isFloatingVisible.send(isVisible)
        }
    }

    fun setIsInFavorites(inFavorites: Boolean) {
        viewModelScope.launch {
            _isAdvertInFavorites.send(inFavorites)
        }
    }
    fun setFavChecked(isChecked: Boolean) {
        viewModelScope.launch {
            _isFavChecked.send(isChecked)
        }
    }
}