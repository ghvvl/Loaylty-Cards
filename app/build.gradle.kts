import com.android.build.gradle.ProguardFiles.ProguardFile
import org.gradle.kotlin.dsl.sourceSets
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.android.application)
}

kotlin {
    androidTarget()

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
            export(projects.features.impl.widget)
            export(libs.deeplinks)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.splash)
            implementation(libs.androidx.activity)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.data.storage.impl.database)
            implementation(projects.data.storage.impl.loyaltyCards)
            implementation(projects.data.storage.impl.widget)
            implementation(projects.features.impl.root)
            implementation(projects.features.impl.loyaltyCardsList)
            implementation(projects.features.impl.loyaltyCardDetails)
            implementation(projects.features.impl.addLoyaltyCard)
            implementation(projects.features.impl.deepLinks)
            implementation(projects.features.impl.widget)
            implementation(projects.features.impl.home)
            implementation(projects.features.impl.widgetsList)
            implementation(projects.features.impl.widgetDetails)

            implementation(compose.material3)

            implementation(libs.decompose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        iosMain.dependencies {
            api(projects.features.impl.widget)
            api(libs.decompose)
            api(libs.essenty)
            api(libs.essenty.backHandler)
            api(libs.essenty.stateKeeper)
            api(libs.deeplinks)
        }
    }
}

android {
    namespace = "com.vvl.ylc"

    defaultConfig {
        applicationId = "com.vvl.ylc"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures.compose = true

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
            packageName = "com.vvl.ylc"
            packageVersion = "1.0.0"
        }
    }
}