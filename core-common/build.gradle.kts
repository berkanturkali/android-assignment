import Dependencies.ProjectLib.CORE_MODEL
import Dependencies.View.glide
import Dependencies.View.glideAnnotation
import Dependencies.View.swipeRefreshLayout

plugins {
    androidLibrary
    androidLibraryCompose
}

android.namespace = "com.arabam.android.assignment.core.common"

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

    implementAll(Dependencies.Compose.COMPOSE_UI, Dependencies.Compose.COMPOSE_MATERIAL)

    //glide
    implementation(glide)
    annotationProcessor(glideAnnotation)
}
