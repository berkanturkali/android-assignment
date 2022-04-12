import Dependencies.Network.paging
import Dependencies.ProjectLib.common
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.details
import Dependencies.ProjectLib.domain

plugins {
    androidLib
    daggerHilt
    parcelize
}

dependencies {
    //project lib
    implementation(project(details))
    implementation(project(core))
    implementation(project(domain))
    implementation(project(common))
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
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

    //androidx
    implementAll(Dependencies.AndroidX.components)

    implementAll(Dependencies.Navigation.components)

    //coroutines
    implementAll(Dependencies.Coroutines.components)

}
