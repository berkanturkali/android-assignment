import Dependencies.ProjectLib.database
import Dependencies.ProjectLib.domain
import Dependencies.ProjectLib.remote

plugins {
    kotlinLibrary
    kotlin(kotlinKapt)
}

dependencies {
    implementation(project(database))
    implementation(project(remote))
    implementation(project(domain))
    implementAll(Dependencies.Network.components)
}