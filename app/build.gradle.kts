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

    //We need this kludge because of moko-permissions that doesn't support jvm target
    //jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "App"
            export(libs.decompose)
            export(libs.essenty)
            export(libs.essenty.backHandler)
            export(libs.essenty.stateKeeper)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(projects.features.impl.widget)

            implementation(libs.androidx.splash)
            implementation(libs.androidx.activity)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.data.storage.impl.loyaltyCards)
            implementation(projects.features.impl.root)
            implementation(projects.features.impl.loyaltyCardsList)
            implementation(projects.features.impl.loyaltyCardDetails)
            implementation(projects.features.impl.addLoyaltyCard)

            implementation(compose.material3)
            implementation(libs.decompose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
            implementation(libs.datastore)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        iosMain.dependencies {
            api(libs.decompose)
            api(libs.essenty)
            api(libs.essenty.backHandler)
            api(libs.essenty.stateKeeper)
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
        mainClass = "com.vvl.loyalty_cards.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.vvl.loyalty_cards"
            packageVersion = "1.0.0"
        }
    }
}