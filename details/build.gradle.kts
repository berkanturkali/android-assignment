import Dependencies.ProjectLib.common
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.domain
import Dependencies.Util.jsoup

plugins {
    androidLib
    parcelize
    daggerHilt
}
dependencies {

    //project lib
    implementation(project(core))
    implementation(project(domain))
    implementation(project(common))

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
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
    //androidx
    implementAll(Dependencies.AndroidX.components)

    //navigation component
    implementAll(Dependencies.Navigation.components)

    //coroutines
    implementation(Dependencies.Coroutines.kotlinAndroid)

}
