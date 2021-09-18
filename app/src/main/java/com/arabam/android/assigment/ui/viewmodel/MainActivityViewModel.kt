package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.assigment.utils.ShowEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _showProgress = Channel<ShowEvent.ShowProgressEvent>()

    val showProgress get() = _showProgress.receiveAsFlow()

    fun showProgress(show: Boolean) {
        viewModelScope.launch {
            _showProgress.send(ShowEvent.ShowProgressEvent(show))
        }
    }

}