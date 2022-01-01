import Dependencies.Logger.timber
import Dependencies.ProjectLib.commons
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.data
import Dependencies.ProjectLib.database
import Dependencies.ProjectLib.details
import Dependencies.ProjectLib.domain
import Dependencies.ProjectLib.favorites
import Dependencies.ProjectLib.listing
import Dependencies.ProjectLib.remote
import Dependencies.View.fab

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    safeArgs
    daggerHilt
}

android {
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdk = Config.Version.minSdkVersion
        compileSdk = Config.Version.compileSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(core))
    implementation(project(domain))
    implementation(project(remote))
    implementation(project(data))
    implementation(project(database))
    implementation(project(commons))
    implementation(project(listing))
    implementation(project(details))
    implementation(project(favorites))
    implementAll(Dependencies.View.components)
    implementation(fab)
    implementation(timber)

    Dependencies.AndroidX.run {
        implementation(coreKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
    }
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
}

hilt {
    enableExperimentalClasspathAggregation = true
}

kapt {
    javacOptions {
        // These options are normally set automatically via the Hilt Gradle plugin, but we
        // set them manually to workaround a bug in the Kotlin 1.5.20
        option("-Adagger.fastInit=ENABLED")
        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")

    }
}