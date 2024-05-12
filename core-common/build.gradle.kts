import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementProject

plugins {
    alias(libs.plugins.arabam.android.library)
    alias(libs.plugins.arabam.android.library.compose)
}

android.namespace = "com.arabam.android.assignment.core.common"

dependencies {

    implementProject(Modules.CORE_MODEL)

    implementAll(
        libs.androidx.swipe.refresh,
        libs.google.material,
        libs.androidx.navigation.fragment.ktx,
        libs.androidx.navigation.ui.ktx,
        libs.bumptech.glide
    )
}
