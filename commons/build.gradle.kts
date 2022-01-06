import Dependencies.ProjectLib.domain
import Dependencies.View.glide
import Dependencies.View.glideAnnotation

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
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
    implementation(project(domain))
    Dependencies.View.run {
        implementAll(components)
        implementation(swipeRefreshLayout)

    }
    implementation(glide)
    annotationProcessor(glideAnnotation)
}