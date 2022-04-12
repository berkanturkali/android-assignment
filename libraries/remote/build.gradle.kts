import Dependencies.Network.pagingCommon
import Dependencies.ProjectLib.domain

plugins {
    kotlinLib
}
dependencies {
    implementation(project(domain))
    implementation(pagingCommon)
    implementAll(Dependencies.Network.components)
}
