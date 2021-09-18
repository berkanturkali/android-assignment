package com.arabam.android.assigment.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseViewModel:ViewModel() {

    inline fun <T> launchAsync(
        crossinline execute: suspend () -> Response<T>,
        crossinline onSuccess: (T) -> Unit,
        crossinline onError: (String) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result = withContext(Dispatchers.IO) { execute() }
                if (result.isSuccessful) {
                    onSuccess(result.body()!!)
                } else {
                    onError("Bir şeyler ters gitti.")
                }
            } catch (e: Exception) {
                onError(e.message!!)
            }
        }
    }
}