import BuildType.Companion.DEBUG
import Dependencies.ProjectLib.commons
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.domain
import Dependencies.Util.jsoup

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    parcelize
    daggerHilt
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
        named(DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
    }
}

dependencies {
    implementation(project(core))
    implementation(project(domain))
    implementation(project(commons))
    Dependencies.View.run {
        implementAll(components)
        implementation(swipeRefreshLayout)
        implementation(viewPager)
    }
    implementation(jsoup)
    implementation(Dependencies.DI.daggerHiltAndroid)
    implementAll(Dependencies.AndroidX.components)
    implementAll(Dependencies.Coroutines.components)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
}
