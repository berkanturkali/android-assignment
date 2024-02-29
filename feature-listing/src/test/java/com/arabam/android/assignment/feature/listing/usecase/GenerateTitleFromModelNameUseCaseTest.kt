package com.arabam.android.assignment.feature.listing.usecase

import com.google.common.truth.Truth
import org.junit.Test

class GenerateTitleFromModelNameUseCaseTest {

    private val generateTitleFromModelName = GenerateTitleFromModelNameUseCase()

    @Test
    fun `check that generateTitleFromModelName generates title as expected`() {
        val modelName = "honda-civic-1-6-i-vtec-premium"
        val expectedTitle = "Honda"
        val title = generateTitleFromModelName(modelName)
        Truth.assertThat(title).isEqualTo(expectedTitle)
    }
}