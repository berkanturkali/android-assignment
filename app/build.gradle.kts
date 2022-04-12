import Dependencies.ProjectLib.common
import Dependencies.ProjectLib.core
import Dependencies.ProjectLib.data
import Dependencies.ProjectLib.database
import Dependencies.ProjectLib.details
import Dependencies.ProjectLib.domain
import Dependencies.ProjectLib.favorites
import Dependencies.ProjectLib.listing
import Dependencies.ProjectLib.remote

plugins {
    androidApp
}
dependencies {
    implementation(project(core))
    implementation(project(domain))
    implementation(project(remote))
    implementation(project(data))
    implementation(project(database))
    implementation(project(common))
    implementation(project(listing))
    implementation(project(details))
    implementation(project(favorites))
}

android.buildFeatures.dataBinding = true

hilt {
    enableAggregatingTask = true
}
android {
    kapt {
        correctErrorTypes = true
    }
}
