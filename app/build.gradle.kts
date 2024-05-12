import BuildType.Companion.Debug
import BuildType.Companion.Release
import plugin.com.arabam.android.assignment.Modules
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.implementAllModules

plugins {
    alias(libs.plugins.arabam.android.application)
    alias(libs.plugins.arabam.android.application.compose)
    alias(libs.plugins.arabam.android.hilt)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    defaultConfig {
        applicationId = "com.arabam.android.assignment"
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        named(Debug.name) {
            isMinifyEnabled = Debug.isMinifyEnabled
            versionNameSuffix = Debug.versionNameSuffix
            isTestCoverageEnabled = Debug.isTestCoverageEnabled
        }
        named(Release.name) {
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
            isMinifyEnabled = Release.isMinifyEnabled
            isMinifyEnabled = Release.isMinifyEnabled
            isTestCoverageEnabled = Release.isTestCoverageEnabled
            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            signingConfig = signingConfigs.getByName(Debug.name)
        }
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    kapt {
        correctErrorTypes = true
    }
    namespace = "com.arabam.android.assignment"
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementAllModules(
        Modules.CORE_DATASTORE,
        Modules.CORE_MODEL,
        Modules.CORE_NETWORK,
        Modules.CORE_DATA,
        Modules.CORE_CACHE,
        Modules.CORE_COMMON,
        Modules.FEATURE_LISTING,
        Modules.FEATURE_DETAILS,
        Modules.FEATURE_FAVORITES,
    )

    implementAll(
        libs.androidx.navigation.ui.ktx,
        libs.androidx.navigation.fragment.ktx
    )

}