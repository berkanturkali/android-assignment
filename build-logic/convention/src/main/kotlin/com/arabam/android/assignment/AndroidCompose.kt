package com.arabam.android.assignment

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.kotlinOptions
import plugin.com.arabam.android.assignment.libs

/**
 * Configures Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("androidXComposeCompiler").get().toString()
        }


        dependencies {
            val bom = libs.findLibrary("androidx-compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("androidx-compose-ui-tooling-preview").get())
            add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())

            implementAll(
                libs.findLibrary("androidx-compose-constraint-layout").get(),
                libs.findLibrary("androidx-compose-material").get(),
                libs.findLibrary("androidx-compose-runtime-livedata").get(),

                )

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
