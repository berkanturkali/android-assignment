import Dependencies.ProjectLib.domain
import Dependencies.View.glide
import Dependencies.View.glideAnnotation
import Dependencies.View.swipeRefreshLayout

plugins {
    androidLib
}

dependencies {

    //project lib
    implementation(project(domain))

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
