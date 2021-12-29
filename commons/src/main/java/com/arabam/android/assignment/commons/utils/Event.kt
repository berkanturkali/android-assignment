package com.arabam.android.assignment.commons.utils

sealed class Event

sealed class ShowEvent : Event(){
    data class ShowProgressEvent(val show:Boolean): ShowEvent()
}

sealed class ClickEvent: Event(){
    data class SortItemClick(val isClicked:Boolean): ClickEvent()
    data class YearItemClick(val isClicked:Boolean): ClickEvent()
}