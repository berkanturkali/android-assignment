import Dependencies.ProjectLib.CORE_CACHE
import Dependencies.ProjectLib.CORE_MODEL
import Dependencies.ProjectLib.CORE_NETWORK

plugins {
    androidLibrary
}

android.namespace = "com.arabam.android.assignment.core.data"

dependencies {

    implementation(project(CORE_CACHE))
    implementation(project(CORE_NETWORK))
    implementation(project(CORE_MODEL))
    implementation(Dependencies.Network.pagingCommon)
    implementAll(Dependencies.Network.components)
}