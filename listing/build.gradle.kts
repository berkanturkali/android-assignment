import Dependencies.Network.paging
import Dependencies.ProjectLib.commons
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.details
import Dependencies.ProjectLib.domain

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
    parcelize
    safeArgs
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
    implementation(project(details))
    implementation(project(core))
    implementation(project(domain))
    implementation(project(commons))
    implementation(paging)
    Dependencies.View.run {
        implementAll(components)
        implementation(swipeRefreshLayout)
        implementation(fab)
    }
    implementation(Dependencies.DI.daggerHiltAndroid)
    implementAll(Dependencies.AndroidX.components)
    implementAll(Dependencies.Coroutines.components)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
}