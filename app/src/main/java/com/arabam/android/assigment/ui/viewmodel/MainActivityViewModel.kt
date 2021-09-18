package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assigment.utils.ClickEvent
import com.arabam.android.assigment.utils.ShowEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _showProgress = Channel<ShowEvent.ShowProgressEvent>()

    val showProgress get() = _showProgress.receiveAsFlow()

    private val _sortClick = Channel<ClickEvent.SortItemClick>()

    val sortClick get() = _sortClick.receiveAsFlow()

    private val _yearFilterClick = Channel<ClickEvent.YearItemClick>()

    val yearFilterClick = _yearFilterClick.receiveAsFlow()

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
}