import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.library)

}
android.namespace = "com.arabam.android.assignment.core.network"
android.defaultConfig.buildConfigField("String", "BASE_URL", "\"http://sandbox.arabamd.com/\"")

dependencies {

    implementAllModules(
        Modules.CORE_MODEL,
        Modules.CORE_COMMON
    )
    implementAll(
        libs.retrofit.core,
        libs.okhttp.logging,
        libs.okhttp,
        libs.gson,
        libs.androidx.paging3,
        libs.retrofit.gson.converterfactory
    )
}