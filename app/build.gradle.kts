import com.android.build.gradle.ProguardFiles.ProguardFile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
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

dependencies {
    implementation(projects.features.impl.root)
    implementation(projects.features.impl.widget)

    implementation(libs.androidx.activity)
    implementation(libs.material.compose)
    implementation(libs.decompose)
}