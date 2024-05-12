import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.library)
}

android.namespace = "com.arabam.android.assignment.core.datastore"

dependencies {

    implementAllModules(
        Modules.CORE_DATA,
        Modules.CORE_COMMON,
    )

    implementAll(
        libs.androidx.datastore
    )
}
