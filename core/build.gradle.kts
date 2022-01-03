import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Logger.timber
import Dependencies.ProjectLib.commons
import Dependencies.ProjectLib.data
import Dependencies.ProjectLib.database
import Dependencies.ProjectLib.domain
import Dependencies.ProjectLib.remote
import Dependencies.View

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
}
android {
    compileSdk = Config.Version.compileSdkVersion
    defaultConfig {
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
    }
}
dependencies {
    implementation(project(commons))
    implementation(project(remote))
    implementation(project(domain))
    implementation(project(data))
    implementation(DI.daggerHiltAndroid)
    implementation(project(database))
    implementation(Coroutines.core)
    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(View.appCompat)
    implementation(Dependencies.Network.gson)
    implementAll(Dependencies.Network.components)
    implementAll(Dependencies.DB.components)
    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(View.swipeRefreshLayout)
    implementation(Dependencies.Util.jetpackDataStore)
    implementation(timber)
    kapt(DI.AnnotationProcessor.daggerHilt)
    kapt(Dependencies.DB.roomCompiler)
}