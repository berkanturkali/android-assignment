import Dependencies.DB.roomCommon
import Dependencies.DB.roomCompiler
import Dependencies.ProjectLib.domain

plugins {
    kotlinLib
}

dependencies {
    implementation(project(domain))
    implementation(roomCommon)
    kapt(roomCompiler)
}
