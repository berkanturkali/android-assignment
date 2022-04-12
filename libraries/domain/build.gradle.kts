import Dependencies.Network.pagingCommon

plugins {
    kotlinLib
}

dependencies {
    implementation(pagingCommon)
    implementAll(Dependencies.Network.components)
}
