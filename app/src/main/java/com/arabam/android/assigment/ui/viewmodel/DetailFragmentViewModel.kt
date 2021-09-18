package com.arabam.android.assigment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arabam.android.assigment.base.BaseViewModel
import com.arabam.android.assigment.data.Resource
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.data.repository.AdvertRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val repo: AdvertRepo,
) : BaseViewModel() {

    private val _advert = MutableLiveData<Resource<DetailAdvert>>()
    val advert: LiveData<Resource<DetailAdvert>> get() = _advert

    fun getAdvert(id:Int){
        _advert.value = Resource.Loading()
        launchAsync(execute = {
            repo.advert(id)
        },
            onSuccess = {
                _advert.value =  Resource.Success(it)
            },
            onError = {
                _advert.value = Resource.Error(it)
            }
        )
    }

}