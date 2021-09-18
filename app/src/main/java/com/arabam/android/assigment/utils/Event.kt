package com.arabam.android.assigment.utils

sealed class Event

open class ShowEvent : Event(){
    data class ShowProgressEvent(val show:Boolean): ShowEvent()
}