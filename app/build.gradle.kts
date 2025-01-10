import com.android.build.gradle.ProguardFiles.ProguardFile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.vvl.loyalty_cards"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vvl.loyalty_cards"
        minSdk = 30
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.material)
    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
    implementation(libs.barcode.scanning)
    implementation(libs.zxing.android.embedded)
    implementation(libs.zxing)
}