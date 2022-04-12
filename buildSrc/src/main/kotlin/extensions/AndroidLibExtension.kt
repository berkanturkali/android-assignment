package extensions


import org.gradle.api.JavaVersion
import BuildType.Companion.Debug
import BuildType.Companion.Release
import com.android.build.api.dsl.LibraryExtension

val ProjectExtension.Companion.AndroidLib: ProjectExtension
    get() = AndroidLibExtension()

private class AndroidLibExtension : ProjectExtension {

    override val name: String get() = "android"

    override fun configure(extension: Any) {
        if (extension !is LibraryExtension) return
        extension.apply {
            compileSdk = Config.Version.compileSdkVersion
            defaultConfig {
                minSdk =  Config.Version.minSdkVersion
                targetSdk = Config.Version.targetSdkVersion
            }

            buildTypes {
                named(Debug.name) {
                    isMinifyEnabled = Debug.isMinifyEnabled
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
                named(Release.name) {
                    isMinifyEnabled = Release.isMinifyEnabled
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildFeatures.dataBinding = true
        }
    }
}