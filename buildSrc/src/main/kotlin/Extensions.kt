import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

internal val PluginManager.kotlinAndroid: Unit
    get() {
        apply("org.jetbrains.kotlin.android")
    }

internal val PluginManager.androidModule: Unit
    get() {
        apply("com.android.library")
    }

internal val PluginManager.kotlinKapt: Unit
    get() {
        apply("kotlin-kapt")
    }

internal val PluginManager.daggerHilt: Unit
    get() {
        apply("dagger.hilt.android.plugin")
    }

internal val PluginManager.safeArgs: Unit
    get() {
        apply("androidx.navigation.safeargs.kotlin")
    }

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = id("arabam.android.application")

val PluginDependenciesSpec.androidApplicationCompose: PluginDependencySpec
    get() = id("arabam.android.application.compose")

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = id("arabam.android.library")

val PluginDependenciesSpec.androidLibraryCompose: PluginDependencySpec
    get() = id("arabam.android.library.compose")

val PluginDependenciesSpec.androidFeature: PluginDependencySpec
    get() = id("arabam.android.feature")

val PluginDependenciesSpec.daggerHilt: PluginDependencySpec
    get() = id("dagger.hilt.android.plugin")

val PluginDependenciesSpec.parcelize: PluginDependencySpec
    get() = id("kotlin-parcelize")

val PluginDependenciesSpec.kotlinLib: PluginDependencySpec
    get() = id("kotlinLibrary")

fun DependencyHandler.implementation(dependency: Any) = add(
    "implementation", dependency
)

fun DependencyHandler.kapt(dependency: Any) = add(
    "kapt", dependency
)

fun DependencyHandler.implementAll(dependencies: List<String>) {
    dependencies.forEach(::implementation)
}

fun DependencyHandler.implementAll(vararg dependencies: Any) {
    dependencies.forEach(::implementation)
}

fun DependencyHandler.implementPlatform(dependency: Any) = add(
    "implementation", platform(dependency)
)

fun DependencyHandler.testImplementation(dependency: Any) = add(
    "testImplementation", dependency
)

fun DependencyHandler.androidTestImplementation(dependency: Any) = add(
    "androidTestImplementation", dependency
)

fun DependencyHandler.debugImplementation(dependency: Any) = add(
    "debugImplementation", dependency
)

fun DependencyHandler.androidTestImplementation(vararg dependencies: Any) {
    dependencies.forEach(::androidTestImplementation)
}


fun DependencyHandler.implementAllTests(vararg dependencies: Any) {
    dependencies.forEach(::testImplementation)
}


fun DependencyHandler.implementAllAndroidTests(vararg dependencies: Any) {
    dependencies.forEach(::androidTestImplementation)
}

fun DependencyHandler.implementAllTests(dependencyList: List<String>) {
    dependencyList.forEach(::testImplementation)
}

fun DependencyHandler.implementAllAndroidTests(dependencyList: List<String>) {
    dependencyList.forEach(::androidTestImplementation)
}

fun DependencyHandler.testImplementation(vararg dependencies: Any) {
    dependencies.forEach(::testImplementation)
}
