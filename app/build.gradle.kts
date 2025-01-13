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
    implementation(projects.data.storage.impl.loyaltyCards)

    implementation(projects.features.impl.root)
    implementation(projects.features.impl.loyaltyCardsList)
    implementation(projects.features.impl.loyaltyCardDetails)
    implementation(projects.features.impl.addLoyaltyCard)
    implementation(projects.features.impl.widget)

    implementation(libs.androidx.activity)
    implementation(libs.material.compose)
    implementation(libs.decompose)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin)
    implementation(libs.koin.android)
}