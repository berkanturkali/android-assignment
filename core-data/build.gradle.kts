import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.library)
}

android.namespace = "com.arabam.android.assignment.core.data"

dependencies {

    implementAllModules(
        Modules.CORE_CACHE,
        Modules.CORE_NETWORK,
        Modules.CORE_MODEL,
    )

    implementAll(
        libs.androidx.paging3,
        libs.retrofit.core,
    )
}