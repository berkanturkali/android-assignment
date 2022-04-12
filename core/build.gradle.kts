import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.ProjectLib.common
import Dependencies.ProjectLib.data
import Dependencies.ProjectLib.database
import Dependencies.ProjectLib.domain
import Dependencies.ProjectLib.remote
import Dependencies.View

plugins {
    androidLibrary
    daggerHilt
}

android.defaultConfig.buildConfigField("int", "databaseVersion", 1.toString())
android.defaultConfig.buildConfigField("String", "databaseName", "\"arabam_db\"")
android.defaultConfig.buildConfigField("String", "BASE_URL", "\"http://sandbox.arabamd.com/\"")

dependencies {

    //project lib
    implementation(project(common))
    implementation(project(remote))
    implementation(project(domain))
    implementation(project(data))
    implementation(project(database))

    //hilt
    implementation(DI.daggerHiltAndroid)
    kapt(DI.AnnotationProcessor.daggerHilt)

    //coroutines
    implementation(Coroutines.kotlinAndroid)

    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(View.appCompat)

    //remote stuff
    implementation(Dependencies.Network.gson)
    implementAll(Dependencies.Network.components)

    //cache
    implementAll(Dependencies.DB.components)
    kapt(Dependencies.DB.roomCompiler)

    implementation(Dependencies.Navigation.navigationFragmentKtx)

    //view
    implementation(View.swipeRefreshLayout)

    //jetpack datastore
    implementation(Dependencies.Util.jetpackDataStore)


}
