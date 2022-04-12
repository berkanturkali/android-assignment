package plugin

import Dependencies.Coroutines
import Dependencies.DI
import extensions.JavaExtension
import extensions.KotlinExtension
import extensions.ProjectExtension
import implementation
import kotlinKapt

class KotlinLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(KOTLIN_PLUGIN_ID)
            kotlinKapt
        }

    override val libraryConfig: LibraryConfig
        get() = {
            implementation(Coroutines.core)
            implementation(DI.javaxInject)
        }

    override val extensions: Array<ProjectExtension>
        get() = arrayOf(
            ProjectExtension.KotlinExtension,
            ProjectExtension.JavaExtension
        )

    private companion object {
        const val KOTLIN_PLUGIN_ID: String = "kotlin"
    }
}