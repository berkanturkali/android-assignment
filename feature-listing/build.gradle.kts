import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_DATA
import Dependencies.ProjectLib.CORE_DATASTORE
import Dependencies.ProjectLib.CORE_MODEL
import Dependencies.ProjectLib.FEATURE_DETAILS

plugins {
    androidLibrary
    androidFeature
    parcelize
}

android.namespace = "com.arabam.android.assignment.feature.listing"

dependencies {
    //project lib
    implementation(project(FEATURE_DETAILS))
    implementation(project(CORE_DATASTORE))
    implementation(project(CORE_MODEL))
    implementation(project(CORE_COMMON))
    implementation(project(CORE_DATA))

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.KAPT.daggerHilt)

    implementAll(
        Dependencies.Navigation.components
    )
    implementAll(Dependencies.Coroutines.components)
    implementAll(Dependencies.AndroidX.components)

    implementAll(
        Dependencies.View.materialComponent,
        Dependencies.View.swipeRefreshLayout,
        Dependencies.View.fab,
        Dependencies.Network.paging,
        Dependencies.Compose.COMPOSE_LIVE_DATA
    )


}
