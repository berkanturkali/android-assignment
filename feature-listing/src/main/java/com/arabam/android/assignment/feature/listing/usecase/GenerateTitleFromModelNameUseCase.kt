package com.arabam.android.assignment.feature.listing.usecase

import java.util.Locale
import javax.inject.Inject

class GenerateTitleFromModelNameUseCase @Inject constructor() {
    operator fun invoke(model: String): String {
        return model.split("-")[0].replaceFirstChar { it.titlecase(Locale.getDefault()) }
    }
}