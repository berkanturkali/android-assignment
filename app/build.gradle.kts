import BuildType.Companion.Debug
import BuildType.Companion.Release
import Dependencies.ProjectLib.CORE_COMMON
import Dependencies.ProjectLib.CORE_DATA
import Dependencies.ProjectLib.CORE_CACHE
import Dependencies.ProjectLib.CORE_DATASTORE
import Dependencies.ProjectLib.FEATURE_DETAILS
import Dependencies.ProjectLib.CORE_MODEL
import Dependencies.ProjectLib.FEATURE_FAVORITES
import Dependencies.ProjectLib.FEATURE_LISTING
import Dependencies.ProjectLib.CORE_NETWORK

plugins {
    androidApplication
    androidApplicationCompose

}

android {
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        testInstrumentationRunner = Config.Android.testInstrumentationRunner

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
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(project(CORE_DATASTORE))
    implementation(project(CORE_MODEL))
    implementation(project(CORE_NETWORK))
    implementation(project(CORE_DATA))
    implementation(project(CORE_CACHE))
    implementation(project(CORE_COMMON))
    implementation(project(FEATURE_LISTING))
    implementation(project(FEATURE_DETAILS))
    implementation(project(FEATURE_FAVORITES))
}