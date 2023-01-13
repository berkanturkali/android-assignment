import Dependencies.DB.Version.room_version
import Dependencies.Network.Version.gson_version
import Dependencies.Test.AndroidTest.Version.ANDROIDX_TEST_EXT_VERSION
import Dependencies.Test.AndroidTest.Version.ESPRESSO_CORE_VERSION
import Dependencies.Test.AndroidTest.Version.JUNIT_EXT_VERSION
import Dependencies.Test.UnitTest.Version.JUNIT_VERSION
import Dependencies.Test.UnitTest.Version.MOCKK_VERSION
import Dependencies.Test.UnitTest.Version.MOCK_WEB_SERVER_VERSION
import Dependencies.Test.UnitTest.Version.TRUTH_VERSION
import Dependencies.Util.Version.dataStore
import Dependencies.View.Version.glideVersion
import Dependencies.View.Version.viewPager2

object GradlePlugins{
    object Version {
        const val KOTLIN: String = "1.7.20"
        const val ANDROID_GRADLE: String = "7.2.2"
        const val NAVIGATION_COMPONENT_SAFEARGS: String = "2.5.3"
        const val DAGGER_HILT_ANDROID: String = "2.43"
    }

    const val KOTLIN: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val ANDROID_GRADLE: String = "com.android.tools.build:gradle:${Version.ANDROID_GRADLE}"
    const val NAVIGATION_COMPONENT_SAFEARGS: String =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.NAVIGATION_COMPONENT_SAFEARGS}"
    const val DAGGER_HILT: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.DAGGER_HILT_ANDROID}"
}

object Config {
    object Version {
        const val minSdkVersion: Int = 21
        const val compileSdkVersion: Int = 33
        const val targetSdkVersion: Int = 33
        const val versionName: String = "1.0"
        const val versionCode: Int = 1
    }

    object Android {
        const val applicationId: String = "com.arabam.android.assignment"
        const val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface Library {
    val components: List<String>
}

object Dependencies {

    object Compose {
        object Bom {
            const val COMPOSE_BOM = "androidx.compose:compose-bom:2022.12.00"
        }

        object Version {
            const val COMPOSE_KOTLIN_COMPILER_VERSION = "1.3.2"
        }

        const val COMPOSE_UI = "androidx.compose.ui:ui"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material"
        const val COMPOSE_UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val COMPOSE_UI_TOOLING_PREV = "androidx.compose.ui:ui-tooling-preview"
        const val COMPOSE_UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
        const val COMPOSE_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose"
        const val COMPOSE_LIVE_DATA = "androidx.compose.runtime:runtime-livedata"
    }

    object AndroidX : Library {
        object Version {
            const val coreKtx: String = "1.7.0"
            const val fragment: String = "1.4.0-alpha07"
            const val appCompat: String = "1.5.1"
        }

        const val coreKtx: String = "androidx.core:core-ktx:${Version.coreKtx}"

        const val fragmentKtx: String = "androidx.fragment:fragment-ktx:${Version.fragment}"
        const val appCompat: String = "androidx.appcompat:appcompat:${Version.appCompat}"

        override val components: List<String>
            get() = listOf(
                fragmentKtx, coreKtx
            )
    }

    object Navigation : Library {
        object Version {
            const val navigation: String = "2.5.3"
        }

        const val navigationFragmentKtx: String =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navigationUiKtx: String =
            "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
        override val components: List<String>
            get() = listOf(
                navigationFragmentKtx, navigationUiKtx
            )
    }

    object View : Library {
        object Version {
            const val materialComponent: String = "1.7.0"

            const val constraintLayout: String = "2.0.0-beta6"
            const val swipeRefreshLayout: String = "1.1.0"
            const val viewPager2: String = "2:1.1.0-beta01"
            const val glideVersion: String = "4.12.0"
        }

        const val fab = "com.github.clans:fab:1.6.4"


        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"

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
            materialComponent, constraintLayout
        )
    }

    object Network : Library {
        object Version {
            const val okhttp: String = "4.9.0"
            const val retrofit: String = "2.9.0"
            const val gson_version = "2.8.7"
            const val paging = "3.0.1"
        }

        const val paging = "androidx.paging:paging-runtime:${Version.paging}"
        const val pagingCommon = "androidx.paging:paging-common-ktx:${Version.paging}"
        private const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        private const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        private const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val gson_converter: String =
            "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val gson = "com.google.code.gson:gson:$gson_version"

        override val components: List<String> = listOf(
            okhttp, loggingInterceptor, retrofit, gson_converter, gson
        )
    }

    object DI {
        object Version {
            const val javaxInject: String = "1"
            const val daggerHilt: String = "2.43"
            const val hiltFragment: String = "1.0.0"
        }

        object KAPT {
            const val daggerHilt: String =
                "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
        }

        const val javaxInject: String = "javax.inject:javax.inject:${Version.javaxInject}"
        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltFragment: String =
            "androidx.hilt:hilt-navigation-fragment:${Version.hiltFragment}"
    }

    object Coroutines : Library {
        object Version {
            const val coroutines: String = "1.4.2"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val kotlinAndroid: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

        override val components: List<String> = listOf(core, kotlinAndroid)
    }

    object DB : Library {
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

    object Logger {
        object Version {
            const val timber = "4.7.1"
        }

        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Util {
        object Version {
            const val dataStore = "1.0.0"
            const val jsoup = "1.13.1"
        }

        const val jetpackDataStore = "androidx.datastore:datastore-preferences:$dataStore"
        const val jsoup = "org.jsoup:jsoup:${Version.jsoup}"
    }

    //region test dependencies
    object Test {

        object UnitTest : Library {
            object Version {
                const val JUNIT_VERSION = "4.13.2"
                const val TRUTH_VERSION = "1.1.3"
                const val MOCK_WEB_SERVER_VERSION = "4.10.0"
                const val MOCKK_VERSION = "1.12.4"
            }

            const val JUNIT = "junit:junit:$JUNIT_VERSION"
            const val TRUTH = "com.google.truth:truth:$TRUTH_VERSION"
            const val MOCK_WEB_SERVER =
                "com.squareup.okhttp3:mockwebserver:$MOCK_WEB_SERVER_VERSION"
            const val MOCKK = "io.mockk:mockk:$MOCKK_VERSION"

            override val components: List<String>
                get() = listOf(JUNIT, TRUTH)
        }

        object AndroidTest : Library {
            object Version {
                const val ESPRESSO_CORE_VERSION = "3.5.0"
                const val JUNIT_EXT_VERSION = "1.1.4"
                const val ANDROIDX_TEST_EXT_VERSION = "1.1.3"

            }

            const val JUNIT_EXT = "androidx.test.ext:junit:$JUNIT_EXT_VERSION"
            const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:$ESPRESSO_CORE_VERSION"
            const val ANDROIDX_TEST_EXT_RUNNER =
                "androidx.test.ext:junit-ktx:$ANDROIDX_TEST_EXT_VERSION"
            override val components: List<String>
                get() = listOf(JUNIT_EXT, ESPRESSO_CORE)
        }
    }
    //endregion

    object ProjectLib {
        const val CORE_DATASTORE: String = ":core-datastore"
        const val CORE_MODEL: String = ":core-model"
        const val CORE_COMMON: String = ":core-common"
        const val CORE_NETWORK: String = ":core-network"
        const val CORE_DATA: String = ":core-data"
        const val CORE_CACHE: String = ":core-cache"
        const val FEATURE_LISTING: String = ":feature-listing"
        const val FEATURE_DETAILS: String = ":feature-details"
        const val FEATURE_FAVORITES: String = ":feature-favorites"
    }
}