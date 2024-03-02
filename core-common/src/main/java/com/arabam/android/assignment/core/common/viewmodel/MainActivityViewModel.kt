package com.arabam.android.assignment.core.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _showBadgeOnFavoritesItem = MutableLiveData<Boolean>()

    val showBadgeOnFavoritesItem: LiveData<Boolean> get() = _showBadgeOnFavoritesItem


    fun setShowBadgeOnFavoritesItem(show: Boolean) {
        _showBadgeOnFavoritesItem.value = show
    }
}