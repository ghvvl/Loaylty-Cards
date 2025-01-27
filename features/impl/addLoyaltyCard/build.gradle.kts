plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()

    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {
            api(projects.features.api.addLoyaltyCard)
            implementation(projects.features.api.root)

            implementation(compose.material3)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.add_loyalty_card"
}