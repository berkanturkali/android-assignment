package plugin

import androidModule
import extensions.AndroidLib
import extensions.ProjectExtension
import kotlinAndroid
import kotlinKapt
import safeArgs
import implementAll
import implementation

class AndroidLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            androidModule
            kotlinAndroid
            kotlinKapt
            safeArgs
        }
    override val libraryConfig: LibraryConfig
        get() = {
            Dependencies.View.run {
                implementAll(components)
            }
            implementation(Dependencies.Logger.timber)
        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidLib)
}