import Dependencies.DI
import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_DATA
import Dependencies.View

plugins {
    androidLibrary
}

dependencies {

    //project lib
    implementation(project(CORE_DATA))
    implementation(project(CORE_COMMON))

    //hilt
    implementation(DI.daggerHiltAndroid)
    kapt(DI.KAPT.daggerHilt)

    //view
    implementation(View.swipeRefreshLayout)

    //jetpack datastore
    implementation(Dependencies.Util.jetpackDataStore)


}
