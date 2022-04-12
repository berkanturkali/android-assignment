import Dependencies.ProjectLib.common
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.details
import Dependencies.ProjectLib.domain

plugins {
    androidLib
    daggerHilt
}

dependencies {
    implementation(project(core))
    implementation(project(domain))
    implementation(project(common))
    implementation(project(details))
    Dependencies.View.run {
        implementation(swipeRefreshLayout)
        implementation(recyclerView)
    }
    implementation(Dependencies.DI.daggerHiltAndroid)
    implementAll(Dependencies.AndroidX.components)
    implementation(Dependencies.DI.hiltFragment)
    implementation(Dependencies.Coroutines.kotlinAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
}
