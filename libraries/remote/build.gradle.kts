import Dependencies.Network.pagingCommon
import Dependencies.ProjectLib.domain

plugins {
    kotlinLibrary
}
dependencies {
    implementation(project(domain))
    api(pagingCommon)
    implementAll(Dependencies.Network.components)
}