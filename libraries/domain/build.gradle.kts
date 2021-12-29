import Dependencies.Network.pagingCommon

plugins {
    kotlinLibrary
}

dependencies {
    implementation(pagingCommon)
    implementAll(Dependencies.Network.components)
}