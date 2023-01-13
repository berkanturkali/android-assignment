import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_DATA
import Dependencies.ProjectLib.CORE_DATASTORE
import Dependencies.ProjectLib.FEATURE_DETAILS
import Dependencies.ProjectLib.CORE_MODEL

plugins {
    androidLibrary
    androidFeature
}

dependencies {
    implementation(project(CORE_DATASTORE))
    implementation(project(CORE_MODEL))
    implementation(project(CORE_COMMON))
    implementation(project(FEATURE_DETAILS))
    implementation(project(CORE_DATA))
    Dependencies.View.run {
        implementation(swipeRefreshLayout)
        implementation(recyclerView)
    }
    implementation(Dependencies.DI.daggerHiltAndroid)
    implementAll(Dependencies.AndroidX.components)
    implementation(Dependencies.DI.hiltFragment)
    implementation(Dependencies.Coroutines.kotlinAndroid)
    kapt(Dependencies.DI.KAPT.daggerHilt)
}
