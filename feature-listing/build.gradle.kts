import Dependencies.Network.paging
import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_DATASTORE
import Dependencies.ProjectLib.CORE_DATA
import Dependencies.ProjectLib.FEATURE_DETAILS
import Dependencies.ProjectLib.CORE_MODEL

plugins {
    androidLibrary
    androidFeature
    parcelize
}

dependencies {
    //project lib
    implementation(project(FEATURE_DETAILS))
    implementation(project(CORE_DATASTORE))
    implementation(project(CORE_MODEL))
    implementation(project(CORE_COMMON))
    implementation(project(CORE_DATA))
    //pagination
    implementation(paging)

    //view
    Dependencies.View.run {
        implementation(swipeRefreshLayout)
        implementation(fab)
        implementation(materialComponent)
    }
    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.KAPT.daggerHilt)

    //androidx
    implementAll(Dependencies.AndroidX.components)

    implementAll(Dependencies.Navigation.components)

    //coroutines
    implementAll(Dependencies.Coroutines.components)

}
