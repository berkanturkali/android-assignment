import Dependencies.ProjectLib.CORE_MODEL
import Dependencies.View.glide
import Dependencies.View.glideAnnotation
import Dependencies.View.swipeRefreshLayout

plugins {
    androidLibrary
}

dependencies {

    //project lib
    implementation(project(CORE_MODEL))

    //swipe refresh
    implementation(swipeRefreshLayout)

    //androidx
    Dependencies.AndroidX.run {
        implementation(coreKtx)
    }

    implementAll(Dependencies.Navigation.components)

    //view
    implementation(Dependencies.View.materialComponent)

    //glide
    implementation(glide)
    annotationProcessor(glideAnnotation)
}
