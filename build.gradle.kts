import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
}

subprojects {
    plugins.matching { it is AppPlugin }.whenPluginAdded {
        configure<ApplicationExtension> {
            buildToolsVersion = libs.versions.buildTools.get()
            compileSdk = libs.versions.android.compileSdk.get().toInt()

            defaultConfig {
                minSdk = libs.versions.android.minSdk.get().toInt()
                targetSdk = libs.versions.android.targetSdk.get().toInt()
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }

            lint {
                checkReleaseBuilds = true
                ignoreTestSources = true

                warningsAsErrors = true
                abortOnError = true

                xmlReport = false
            }

            buildFeatures.buildConfig = false
        }
    }

    plugins.matching { it is LibraryPlugin }.whenPluginAdded {
        configure<LibraryExtension> {
            buildToolsVersion = libs.versions.buildTools.get()
            compileSdk = libs.versions.android.compileSdk.get().toInt()

            defaultConfig.minSdk = libs.versions.android.minSdk.get().toInt()

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }

            lint {
                checkReleaseBuilds = true
                ignoreTestSources = true

                warningsAsErrors = true
                abortOnError = true

                xmlReport = false
            }

            buildFeatures.buildConfig = false
        }
    }
}