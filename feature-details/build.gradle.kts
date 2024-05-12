import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.feature)
}
android.namespace = "com.arabam.android.assignment.feature.details"
dependencies {

    implementAllModules(
        Modules.CORE_MODEL,
        Modules.CORE_COMMON,
        Modules.CORE_DATA,
        Modules.CORE_DATASTORE,
    )

    implementAll(
        libs.androidx.swipe.refresh,
        libs.androidx.view.pager,
        libs.google.material,
        libs.jsoup,
        libs.jetbrains.coroutines,
        libs.coil.compose,
    )
}
