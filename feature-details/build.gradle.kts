import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_DATA
import Dependencies.ProjectLib.CORE_DATASTORE
import Dependencies.ProjectLib.CORE_MODEL
import Dependencies.Util.jsoup

plugins {
    androidLibrary
    androidFeature
    parcelize
}
dependencies {

    //project lib
    implementation(project(CORE_MODEL))
    implementation(project(CORE_COMMON))
    implementation(project(CORE_DATA))
    implementation(project(CORE_DATASTORE))

    //view
    Dependencies.View.run {
        implementation(swipeRefreshLayout)
        implementation(viewPager)
        implementation(materialComponent)
    }

    //jsoup
    implementation(jsoup)

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.KAPT.daggerHilt)
    //androidx
    implementAll(Dependencies.AndroidX.components)

    //navigation component
    implementAll(Dependencies.Navigation.components)

    //coroutines
    implementation(Dependencies.Coroutines.kotlinAndroid)

}
