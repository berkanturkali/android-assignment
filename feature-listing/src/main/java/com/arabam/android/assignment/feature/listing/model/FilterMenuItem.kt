package com.arabam.android.assignment.feature.listing.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.arabam.android.assignment.core.common.R.drawable
import com.arabam.android.assignment.core.common.R.string

enum class FilterMenuItem(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
) {
    SORT(string.sort, drawable.ic_sort),
    FILTER_BY_YEAR(string.filter_by_year, drawable.ic_date),
    FILTER_BY_MODEL(string.filter_by_model, drawable.ic_car)

}