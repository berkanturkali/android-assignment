import Dependencies.DB.Version.room_version
import Dependencies.Network.Version.gson_version
import Dependencies.View.Version.glideVersion
import Dependencies.View.Version.viewPager2

const val kotlinAndroid: String = "android"
const val kotlinKapt: String = "kapt"
const val kotlinVersion: String = "1.5.21"
const val kotlinParcelize: String = "parcelize"
const val ktLintVersion: String = "0.37.2"

object Config {
    object Version {
        const val minSdkVersion: Int = 22
        const val compileSdkVersion: Int = 31
        const val targetSdkVersion: Int = 31
        const val versionName: String = "1.0"
        const val versionCode: Int = 1
    }

    object Android {
        const val applicationId: String = "com.arabam.android.assigment"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }
}

object Plugin {
    object Version {
        const val spotless: String = "5.1.2"
        const val kotlin: String = "1.5.21"
        const val androidGradle: String = "7.0.0"
        const val navigation: String = "2.4.0-alpha07"
        const val daggerHiltAndroid: String = "2.38.1"
    }

    const val spotless: String = "com.diffplug.spotless:spotless-plugin-gradle:${Version.spotless}"
    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val navigationSafeArgs: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"
    const val daggerHilt: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
}


interface Libraries {
    val components: List<String>
}

object Dependencies {
    object AndroidX : Libraries {
        object Version {
            const val coreKtx: String = "1.3.2"
            const val navigation: String = "2.4.0-alpha07"
        }

        const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"
        const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

        const val jsoup = "org.jsoup:jsoup:1.13.1"

        const val timber = "com.jakewharton.timber:timber:4.7.1"

        override val components: List<String>
            get() = listOf(
                coreKtx, navigationFragmentKtx, navigationUiKtx,timber
            )
    }

    object View : Libraries {
        object Version {
            const val materialComponent: String = "1.4.0"
            const val appCompat: String = "1.4.0"
            const val constraintLayout: String = "2.0.0-beta6"
            const val fragment: String = "1.4.0-alpha07"
            const val swipeRefreshLayout: String = "1.1.0"
            const val viewPager2: String = "2:1.1.0-beta01"
            const val glideVersion: String = "4.12.0"
        }

        const val fab = "com.github.clans:fab:1.6.4"
        const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val fragment: String = "androidx.fragment:fragment-ktx:${Version.fragment}"

        const val recyclerView ="androidx.recyclerview:recyclerview:1.2.1"

        const val viewPager: String = "androidx.viewpager2:viewpager$viewPager2"

        const val glide: String = "com.github.bumptech.glide:glide:$glideVersion"

        const val glideAnnotation = "com.github.bumptech.glide:compiler:$glideVersion"

        const val materialComponent: String =
            "com.google.android.material:material:${Version.materialComponent}"
        const val constraintLayout: String =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val swipeRefreshLayout: String =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"

        override val components: List<String> = listOf(
            appCompat, fragment,
            materialComponent, constraintLayout
        )
    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.9.0"
            const val retrofit: String = "2.9.0"
            const val gson_converter: String = "2.8.7"
            const val gson_version = "2.8.7"
            const val paging = "3.0.1"
        }

        const val paging = "androidx.paging:paging-runtime:${Version.paging}"
        const val pagingCommon = "androidx.paging:paging-common-ktx:${Version.paging}"
        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gson_converter: String = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val gson = "com.google.code.gson:gson:$gson_version"

        override val components: List<String> = listOf(
            okhttp, loggingInterceptor, retrofit, gson_converter,Network.gson
        )
    }

    object DI {
        object Version {
            const val javaxInject: String = "1"
            const val daggerHilt: String = "2.38.1"
            const val hiltViewModel: String = "1.0.0-alpha02"
            const val hiltFragment:String = "1.0.0"
        }

        object AnnotationProcessor {
            const val daggerHilt: String =
                "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
            const val jetpackHiltCompiler: String =
                "androidx.hilt:hilt-compiler:${Version.hiltViewModel}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Version.javaxInject}"
        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltViewModel: String =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltViewModel}"
        const val hiltCore: String = "com.google.dagger:hilt-core:${Version.daggerHilt}"
        const val hiltFragment:String = "androidx.hilt:hilt-navigation-fragment:${Version.hiltFragment}"
    }

    object Coroutines : Libraries {
        object Version {
            const val coroutines: String = "1.4.2"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val kotlinAndroid: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, kotlinAndroid)
    }

    object DB:Libraries {
        object Version {
            const val room_version = "2.3.0"
        }

        const val roomRuntime = "androidx.room:room-runtime:$room_version"
        const val roomKtx = "androidx.room:room-ktx:$room_version"
        const val roomCompiler = "androidx.room:room-compiler:$room_version"
        const val roomCommon = "androidx.room:room-common:$room_version"

        override val components: List<String>
            get() = listOf(
               roomKtx, roomRuntime
            )
    }

    object ProjectLib {
        const val app: String = ":app"
        const val core:String = ":core"
        const val domain:String = ":libraries:domain"
        const val commons:String  = ":commons"
        const val remote:String = ":libraries:remote"
        const val data:String = ":libraries:data"
        const val listing:String = ":listing"
        const val database:String = ":libraries:database"
        const val details:String = ":details"
        const val favorites:String = ":favorites"
    }
}