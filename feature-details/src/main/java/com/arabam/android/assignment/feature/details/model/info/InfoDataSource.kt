package com.arabam.android.assignment.feature.details.model.info

import android.content.Context
import com.arabam.android.assignment.core.common.R
import com.arabam.android.assignment.core.common.utils.formatPrice
import com.arabam.android.assignment.core.model.DetailAdvert

fun getInfoList(advert: DetailAdvert, context: Context): List<Info> {
    val list = mutableListOf<Info>()
    list.add(Info.InfoWithText(advert.userInfo.nameSurname))
    list.add(Info.InfoWithText(advert.category.name.replace("/", ">"), R.color.link_color))
    list.add(
        Info.InfoWithText(
            String.format(
                context.getString(R.string.address_tv),
                advert.location.townName,
                advert.location.cityName
            )
        )
    )
    list.add(
        Info.InfoWithTitle(
            context.getString(R.string.price),
            String.format(
                context
                    .getString(R.string.advert_price, advert.price.formatPrice())
            ),
            R.color.primary_color
        )
    )
    list.add(
        Info.InfoWithTitle(
            context.getString(R.string.advert_no), advert.id.toString(), R.color.link_color
        )
    )
    list.add(Info.InfoWithTitle(context.getString(R.string.advert_date), advert.dateFormatted))
    list.add(
        Info.InfoWithTitle(
            context.getString(R.string.advert_series), advert.modelName.split(" ")[0]
        )
    )
    list.add(
        Info.InfoWithTitle(context.getString(R.string.advert_model), advert.modelName.split(" ")[1])
    )
    list.add(
        Info.InfoWithTitle(context.getString(R.string.advert_year), advert.properties[2].value)
    )
    list.add(
        Info.InfoWithTitle(context.getString(R.string.advert_fuel_type), advert.properties[4].value)
    )
    list.add(
        Info.InfoWithTitle(context.getString(R.string.advert_gear_type), advert.properties[3].value)
    )
    list.add(
        Info.InfoWithTitle(
            context.getString(R.string.advert_kilometers), advert.properties[0].value
        )
    )
    return list
}
