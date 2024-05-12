import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import plugin.com.arabam.android.assignment.configureKotlinAndroid
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllAndroidTests
import plugin.com.arabam.android.assignment.implementAllTests
import plugin.com.arabam.android.assignment.libs


class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.kapt")
                apply("arabam.android.hilt")
                apply("kotlin-parcelize")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
            dependencies {
                implementAll(
                    libs.findLibrary("androidx-core").get(),
                    libs.findLibrary("androidx-appcompat").get(),
                    libs.findLibrary("google-material").get(),
                    libs.findLibrary("jakewharton-timber").get(),
                )

                implementAllTests(
                    libs.findLibrary("junit").get()
                )

                implementAllAndroidTests(
                    libs.findLibrary("androidx-test-espresso-core").get(),
                    libs.findLibrary("androidx-test-junit").get(),
                )
            }
        }
    }
}