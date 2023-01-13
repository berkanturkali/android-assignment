import Dependencies.Network.pagingCommon
import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_MODEL

plugins {
    androidLibrary
}

android.defaultConfig.buildConfigField("String", "BASE_URL", "\"http://sandbox.arabamd.com/\"")

dependencies {
    implementation(project(CORE_MODEL))
    implementation(project(CORE_COMMON))
    implementation(pagingCommon)
    implementAll(Dependencies.Network.components)
}