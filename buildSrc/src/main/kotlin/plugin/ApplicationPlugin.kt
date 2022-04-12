package plugin

import Dependencies
import daggerHilt
import extensions.AndroidApp
import extensions.ProjectExtension
import implementAll
import implementation
import kapt
import kotlinAndroid
import kotlinKapt
import safeArgs


class ApplicationPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(APP_PLUGIN_ID)
            kotlinAndroid
            kotlinKapt
            daggerHilt
            safeArgs
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementAll(Dependencies.View.components)
            Dependencies.AndroidX.run {
                implementation(coreKtx)
            }
            implementAll(Dependencies.Navigation.components)
            implementation(Dependencies.Logger.timber)
            implementation(Dependencies.DI.daggerHiltAndroid)
            kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)

        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidApp)

    private companion object {
        const val APP_PLUGIN_ID: String = "com.android.application"
    }
}