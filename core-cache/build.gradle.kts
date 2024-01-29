import Dependencies.ProjectLib.CORE_MODEL

plugins {
    androidLibrary
}

android.defaultConfig.buildConfigField("int", "databaseVersion", 1.toString())
android.defaultConfig.buildConfigField("String", "databaseName", "\"arabam_db\"")

dependencies {
    implementation(project(CORE_MODEL))

    implementAll(Dependencies.DB.components)
    kapt(Dependencies.DB.roomCompiler)
}