import com.android.build.gradle.ProguardFiles.ProguardFile
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.application)
}

kotlin {
    androidTarget()

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(projects.features.impl.widget)
            implementation(projects.data.storage.impl.loyaltyCards)
        }
        commonMain.dependencies {
            implementation(projects.features.impl.root)
            implementation(projects.features.impl.loyaltyCardsList)
            implementation(projects.features.impl.loyaltyCardDetails)
            implementation(projects.features.impl.addLoyaltyCard)

            implementation(libs.androidx.activity)
            implementation(libs.material.compose)
            implementation(libs.decompose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards"

    defaultConfig {
        applicationId = "com.vvl.loyalty_cards"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures.compose = true

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(ProguardFile.OPTIMIZE.fileName),
                "proguard-rules.pro"
            )
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ru.vvl.zangetsu"
            packageVersion = "1.0.0"
        }
    }
}