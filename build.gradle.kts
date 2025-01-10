import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.plugins.android.gradle.get().toString())
        classpath(libs.plugins.kotlin.gradle.get().toString())
        classpath(libs.plugins.compose.gradle.get().toString())
    }
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

    plugins.matching { it is ComposeCompilerGradleSubplugin }.whenPluginAdded {
        configure<ComposeCompilerGradlePluginExtension> {
            featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
            featureFlags.add(ComposeFeatureFlag.PausableComposition)
        }
    }

    plugins.matching { it is JavaPlugin }.whenPluginAdded {
        configure<JavaPluginExtension> {
            toolchain.languageVersion.set(JavaLanguageVersion.of(21))
        }
    }
}