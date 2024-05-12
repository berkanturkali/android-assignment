import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.feature)
}

android.namespace = "com.arabam.android.assignment.feature.listing"

dependencies {
    implementAllModules(
        Modules.FEATURE_DETAILS,
        Modules.CORE_MODEL,
        Modules.CORE_DATASTORE,
        Modules.CORE_DATA,
        Modules.CORE_COMMON,
    )

    implementAll(
        libs.jetbrains.coroutines,
        libs.google.material,
        libs.androidx.swipe.refresh,
        libs.androidx.paging3
    )
}
