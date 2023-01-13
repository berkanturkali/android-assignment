package plugin

import Config
import Dependencies
import com.android.build.gradle.LibraryExtension
import debugImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import implementAll
import implementPlatform
import implementAllTests
import implementAllAndroidTests

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = Config.Android.testInstrumentationRunner

                }
            }

            dependencies {

                implementAll(
                    Dependencies.AndroidX.coreKtx,
                    Dependencies.AndroidX.appCompat,
                    Dependencies.View.materialComponent,
                )


                implementAllTests(
                    Dependencies.Test.UnitTest.components
                )

                implementAllAndroidTests(
                    Dependencies.Test.AndroidTest.JUNIT_EXT,
                    Dependencies.Test.AndroidTest.ESPRESSO_CORE
                )

                implementPlatform(
                    Dependencies.Compose.Bom.COMPOSE_BOM
                )

                implementAll(
                    Dependencies.Compose.COMPOSE_UI,
                    Dependencies.Compose.COMPOSE_MATERIAL,
                    Dependencies.Compose.COMPOSE_UI_TOOLING_PREV,
                )

                //debug implementation
                debugImplementation(Dependencies.Compose.COMPOSE_UI_TOOLING)
                debugImplementation(Dependencies.Compose.COMPOSE_UI_TEST_MANIFEST)
                debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-beta02")

            }
        }
    }
}