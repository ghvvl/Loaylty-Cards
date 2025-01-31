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
        commonMain.dependencies {
            implementation(compose.components.resources)

            implementation(projects.data.storage.api.loyaltyCards)

            api(projects.features.api.loyaltyCardsList)
            implementation(projects.features.api.root)

            implementation(compose.material3)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.essenty.coroutines)
            implementation(libs.coroutines)
        }

        jvmMain.dependencies {
            implementation(libs.coroutines.jvm)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.loyalty_cards_list"
}