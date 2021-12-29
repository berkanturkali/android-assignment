import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories.applyDefault()
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    }
}

    allprojects {
        repositories.applyDefault()
        tasks.withType<KotlinCompile> {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
        configurations.all {
            resolutionStrategy.eachDependency {
                if (requested.group == "org.jetbrains.kotlin") {
                    useVersion(kotlinVersion)
                }
            }
        }
    }

    subprojects {
        applySpotless
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions.freeCompilerArgs +=
                "-Xuse-experimental=" +
                        "kotlin.Experimental," +
                        "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                        "kotlinx.coroutines.InternalCoroutinesApi," +
                        "kotlinx.coroutines.FlowPreview"
        }

    }