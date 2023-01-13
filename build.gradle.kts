buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

        dependencies {
            classpath(GradlePlugins.KOTLIN)
            classpath(GradlePlugins.ANDROID_GRADLE)
            classpath(GradlePlugins.DAGGER_HILT)
        }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}
