import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.versions)
    alias(libs.plugins.detekt)
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

    tasks.withType<KotlinCompile> {
        compilerOptions.apiVersion.set(KotlinVersion.KOTLIN_2_1)
    }

    apply {
        plugin(rootProject.libs.plugins.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.versions.get().pluginId)
    }

    tasks.withType<DependencyUpdatesTask> {
        fun isNonStable(version: String): Boolean {
            val stableKeyword =
                listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
            val regex = "^[0-9,.v-]+(-r)?$".toRegex()
            val isStable = stableKeyword || regex.matches(version)
            return isStable.not()
        }

        rejectVersionIf { isNonStable(candidate.version) && !isNonStable(currentVersion) }
    }

    dependencies {
        //   add("detektPlugins", rootProject.libs.detekt.formatting)
    }

    tasks.withType<Detekt> {
        exclude { it.file.relativeTo(projectDir).startsWith("build") }
    }
    tasks.register("detektAll") {
        allprojects {
            this@register.dependsOn(tasks.withType<Detekt>())
        }
    }
}