package com.arabam.android.assigment.data.model.info

import android.content.Context
import com.arabam.android.assigment.R
import com.arabam.android.assigment.data.model.DetailAdvert
import com.arabam.android.assigment.utils.formatPrice

fun getInfoList(advert: DetailAdvert, context: Context): List<Info> {
    val list = mutableListOf<Info>()
    list.add(Info.InfoWithText(advert.userInfo.nameSurname))
    list.add(Info.InfoWithText(advert.category.name.replace("/", ">"), R.color.link_color))
    list.add(Info.InfoWithText(String.format(context.getString(R.string.address_tv),
        advert.location.townName,
        advert.location.cityName)))
    list.add(Info.InfoWithTitle("Fiyat",
        String.format(context.getString(R.string.advert_price, advert.price.formatPrice())),
        R.color.primaryColor))
    list.add(Info.InfoWithTitle("İlan No", advert.id.toString(), R.color.link_color))
    list.add(Info.InfoWithTitle("İlan Tarihi", advert.dateFormatted))
    list.add(Info.InfoWithTitle("Seri", advert.modelName.split(" ")[0]))
    list.add(Info.InfoWithTitle("Model", advert.modelName.split(" ")[1]))
    list.add(Info.InfoWithTitle("Yıl", advert.properties[2].value))
    list.add(Info.InfoWithTitle("Yakıt Tipi", advert.properties[4].value))
    list.add(Info.InfoWithTitle("Vites Tipi", advert.properties[3].value))
    list.add(Info.InfoWithTitle("Kilometre", advert.properties[0].value))


    return list

}