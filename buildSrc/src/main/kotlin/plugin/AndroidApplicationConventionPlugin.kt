package plugin

import Config
import Dependencies
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.arabam.android.assignment.configureKotlinAndroid
import daggerHilt
import implementAll
import implementation
import kapt
import kotlinKapt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import safeArgs
import debugImplementation
import implementPlatform
import testImplementation
import androidTestImplementation

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                kotlinKapt
                daggerHilt
                safeArgs
            }
            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Config.Version.targetSdkVersion

                dependencies {
                    implementAll(Dependencies.View.components)
                    Dependencies.AndroidX.run {
                        implementation(coreKtx)
                        implementation(appCompat)
                    }
                    implementAll(Dependencies.Navigation.components)
                    implementation(Dependencies.Logger.timber)
                    implementation(Dependencies.DI.daggerHiltAndroid)
                    kapt(Dependencies.DI.KAPT.daggerHilt)

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

                    testImplementation(Dependencies.Test.UnitTest.JUNIT)

                    androidTestImplementation(Dependencies.Test.AndroidTest.JUNIT_EXT)
                    androidTestImplementation(Dependencies.Test.AndroidTest.ESPRESSO_CORE)

                }
            }
        }
    }
}