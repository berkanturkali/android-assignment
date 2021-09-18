package com.arabam.android.assigment.data.model.info

import com.arabam.android.assigment.R

sealed class Info(){
    data class InfoWithTitle(
        val title:String,
        val text:String,
        val color:Int = R.color.black
    ):Info()
    data class InfoWithText(
        val text:String,
        val color:Int = R.color.black
    ):Info()
}