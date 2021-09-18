package com.arabam.android.assigment.data.model.sort

fun getSortList():List<SortItem>{
    val sortList = mutableListOf<SortItem>()
    sortList.add(SortItem("Fiyat",SortTypes.BY_PRICE,SortDirections.ASCENDING))
    sortList.add(SortItem("Fiyat",SortTypes.BY_PRICE,SortDirections.DESCENDING))
    sortList.add(SortItem("Tarih",SortTypes.BY_DATE,SortDirections.ASCENDING))
    sortList.add(SortItem("Tarih",SortTypes.BY_DATE,SortDirections.DESCENDING))
    sortList.add(SortItem("Yıl",SortTypes.BY_YEAR,SortDirections.ASCENDING))
    sortList.add(SortItem("Yıl",SortTypes.BY_DATE,SortDirections.DESCENDING))
    return sortList
}