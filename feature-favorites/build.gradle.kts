import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.feature)
}
android.namespace = "com.arabam.android.assignment.feature.favorites"
dependencies {
    implementAllModules(
        Modules.CORE_DATASTORE,
        Modules.CORE_MODEL,
        Modules.CORE_COMMON,
        Modules.FEATURE_DETAILS,
        Modules.CORE_DATA,
    )

    implementAll(
        libs.androidx.swipe.refresh,
        libs.jetbrains.coroutines
        )
}
