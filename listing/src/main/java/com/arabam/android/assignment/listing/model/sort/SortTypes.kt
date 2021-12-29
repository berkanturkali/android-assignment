package com.arabam.android.assignment.listing.model.sort

enum class SortTypes(val value:Int){

    BY_PRICE(0),
    BY_DATE(1),
    BY_YEAR(2)
}

enum class SortDirections(val value:Int,val text:String){
    ASCENDING(0,"Artan"),
    DESCENDING(1,"Azalan")
}