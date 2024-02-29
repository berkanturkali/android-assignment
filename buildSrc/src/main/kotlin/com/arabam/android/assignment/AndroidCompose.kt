package com.arabam.android.assignment

import Dependencies
import com.android.build.api.dsl.CommonExtension

import org.gradle.api.Project

/**
 * Configures Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *,*>
) {

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = Dependencies.Compose.Version.COMPOSE_KOTLIN_COMPILER_VERSION
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + (
                    "-Xopt-in=kotlin.Experimental," +
                            "androidx.compose.ui.ExperimentalComposeUiApi," +
                            "com.google.accompanist.pager.ExperimentalPagerApi," +
                            "androidx.compose.foundation.ExperimentalFoundationApi," +
                            "com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi," +
                            "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                            "kotlinx.coroutines.InternalCoroutinesApi," +
                            "kotlinx.coroutines.ObsoleteCoroutinesApi," +
                            "kotlinx.coroutines.FlowPreview," +
                            "androidx.compose.ui.ExperimentalComposeUiApi," +
                            "androidx.compose.material.ExperimentalMaterialApi," +
                            "androidx.compose.animation.ExperimentalAnimationApi,"
                    )
        }
    }

}