package plugin


import Config
import Dependencies
import com.android.build.gradle.LibraryExtension
import com.arabam.android.assignment.configureKotlinAndroid
import daggerHilt
import implementAll
import implementation
import kotlinAndroid
import kotlinKapt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import safeArgs
import kapt
import testImplementation
import androidTestImplementation


class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                kotlinAndroid
                kotlinKapt
                daggerHilt
                safeArgs
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Config.Version.targetSdkVersion

                dependencies {
                    implementation(Dependencies.AndroidX.appCompat)
                    implementation(Dependencies.AndroidX.coreKtx)
                    implementAll(Dependencies.View.components)
                    implementation(Dependencies.Logger.timber)
                    implementation(Dependencies.DI.daggerHiltAndroid)
                    kapt(Dependencies.DI.KAPT.daggerHilt)

                    testImplementation(Dependencies.Test.UnitTest.JUNIT)

                    androidTestImplementation(Dependencies.Test.AndroidTest.JUNIT_EXT)
                    androidTestImplementation(Dependencies.Test.AndroidTest.ESPRESSO_CORE)
                }
            }
        }
    }
}