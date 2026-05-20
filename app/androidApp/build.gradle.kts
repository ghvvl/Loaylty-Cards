import com.android.build.gradle.ProguardFiles.ProguardFile

plugins {
    id("com.vvl.cmpPlugin")
    alias(libs.plugins.android.application)
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

dependencies {
    implementation(projects.app.common)
}