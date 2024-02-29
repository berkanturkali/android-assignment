package plugin

import Dependencies
import com.android.build.gradle.LibraryExtension
import com.arabam.android.assignment.configureAndroidCompose
import debugImplementation
import implementAll
import implementPlatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)

            dependencies {
                implementPlatform(
                    Dependencies.Compose.Bom.COMPOSE_BOM
                )

                implementAll(
                    Dependencies.Compose.COMPOSE_UI,
                    Dependencies.Compose.COMPOSE_MATERIAL,
                    Dependencies.Compose.COMPOSE_UI_TOOLING_PREV,
                    Dependencies.Compose.COMPOSE_CONSTRAINT_LAYOUT
                )

                //debug implementation
                debugImplementation(Dependencies.Compose.COMPOSE_UI_TOOLING)
                debugImplementation(Dependencies.Compose.COMPOSE_UI_TEST_MANIFEST)
                debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-beta02")
            }
        }
    }
}