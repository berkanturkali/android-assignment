package com.arabam.android.assignment.feature.listing.usecase

import androidx.test.platform.app.InstrumentationRegistry
import com.arabam.android.assignment.core.common.R.drawable
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GetLogoByTheKeyUseCaseTest {


    private val context = InstrumentationRegistry.getInstrumentation().context

    private val getLogoByTheKeyUseCase = GetLogoByTheKeyUseCase()

    @Test
    fun getLogoByTheKey_returnsResourceId_IfTheKeyIsValid() {
        val key = "ford"
        val expectedResource = drawable.ford_logo
        val result = getLogoByTheKeyUseCase(key, context)
        assertEquals(expectedResource, result)

    }

}