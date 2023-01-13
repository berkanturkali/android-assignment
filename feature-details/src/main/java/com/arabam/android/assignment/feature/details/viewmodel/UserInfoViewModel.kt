package com.arabam.android.assignment.feature.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.utils.Constants.USER_NAME
import com.arabam.android.assignment.core.common.utils.Constants.USER_PHONE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val listOfAvatars = listOf(
        R.drawable.avatar_1_raster,
        R.drawable.avatar_2_raster,
        R.drawable.avatar_3_raster,
        R.drawable.avatar_4_raster,
        R.drawable.avatar_5_raster,
        R.drawable.avatar_6_raster
    )

    private lateinit var phone: String

    private lateinit var name: String

    private var image: Int = -1

    init {
        savedStateHandle.get<String>(USER_PHONE)
            ?.let {
                phone = it
            }
        savedStateHandle.get<String>(USER_NAME)
            ?.let {
                name = it
            }
        val random = getRandom(listOfAvatars.size)
        image = listOfAvatars[random]
    }

    fun getPhone() = phone

    fun getName() = name

    fun getImage() = image

    private fun getRandom(limit: Int): Int {
        return (0 until limit).random()
    }
}
