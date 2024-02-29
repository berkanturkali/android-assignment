import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "arabam.android.application.compose"
            implementationClass = "plugin.AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplication") {
            id = "arabam.android.application"
            implementationClass = "plugin.AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "arabam.android.library.compose"
            implementationClass = "plugin.AndroidLibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "arabam.android.library"
            implementationClass = "plugin.AndroidLibraryConventionPlugin"
        }

        register("androidFeature") {
            id = "arabam.android.feature"
            implementationClass = "plugin.AndroidFeatureConventionPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

object Plugin {

    object Version {
        const val kotlin: String = "1.9.21"
        const val androidGradle: String = "8.1.3"
        const val navigation: String = "2.7.3"
        const val daggerHiltAndroid: String = "2.50"
    }

    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgs: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHilt: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
}

dependencies {
    implementation(Plugin.kotlin)
    implementation(Plugin.androidGradle)
    implementation(Plugin.navigationSafeArgs)
    implementation(Plugin.daggerHilt)
}