import Dependencies.ProjectLib.database
import Dependencies.ProjectLib.domain
import Dependencies.ProjectLib.remote

plugins {
    kotlinLibrary
}

dependencies {
    implementation(project(database))
    implementation(project(remote))
    implementation(project(domain))
    implementation(Dependencies.Network.pagingCommon)
    implementAll(Dependencies.Network.components)
}
