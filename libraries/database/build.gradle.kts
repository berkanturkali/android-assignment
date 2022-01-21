import Dependencies.DB.roomCommon
import Dependencies.DB.roomCompiler
import Dependencies.ProjectLib.domain

plugins {
    kotlinLibrary
    kotlin(kotlinKapt)
}

dependencies {
    implementation(project(domain))
    implementation(roomCommon)
    kapt(roomCompiler)
}
