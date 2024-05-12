pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

include(
    ":app",
    ":feature-listing",
    ":feature-details",
    ":feature-favorites"
)
rootProject.name = "arabam"
include(":core-network")
include(":core-data")
include(":core-cache")
include(":core-model")
include(":core-datastore")
include(":core-common")
