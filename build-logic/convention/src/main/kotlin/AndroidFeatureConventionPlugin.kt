import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import plugin.com.arabam.android.assignment.implementAll
import plugin.com.arabam.android.assignment.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("arabam.android.library")
                apply("arabam.android.library.compose")
                apply("androidx.navigation.safeargs.kotlin")
            }

            dependencies {
                implementAll(
                    libs.findLibrary("androidx-navigation-fragment-ktx").get(),
                    libs.findLibrary("androidx-navigation-ui-ktx").get(),
                )
            }
        }
    }
}