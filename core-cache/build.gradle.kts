import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllTests
import plugin.com.arabam.android.assignment.implementProject

plugins {
    alias(libs.plugins.arabam.android.library)
}

android.defaultConfig.buildConfigField("int", "databaseVersion", 2.toString())
android.defaultConfig.buildConfigField("String", "databaseName", "\"arabam_db\"")

android.namespace = "com.arabam.android.assignment.core.cache"

dependencies {
    implementProject(Modules.CORE_MODEL)

    implementAll(
        libs.androidx.room.ktx,
        libs.androidx.room.runtime
    )

    kapt(libs.androidx.room.compiler)

    implementAllTests(
        libs.google.truth
    )
}