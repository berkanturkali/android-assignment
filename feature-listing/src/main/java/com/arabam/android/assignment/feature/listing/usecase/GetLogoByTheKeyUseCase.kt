package com.arabam.android.assignment.feature.listing.usecase

import android.content.Context
import javax.inject.Inject

class GetLogoByTheKeyUseCase @Inject constructor(){

    companion object{
        private const val LOGO_SUFFIX = "_logo"
    }

    operator fun invoke(key:String,context: Context) : Int {
        return  context.resources.getIdentifier(
            key.lowercase() + LOGO_SUFFIX,
            "drawable",
            context.packageName
        )
    }
}