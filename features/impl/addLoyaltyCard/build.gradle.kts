plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    androidTarget()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
            implementation(projects.common)

            implementation(libs.camera.lifecycle)
            implementation(libs.camera.view)
            implementation(libs.camera.camera2)
            implementation(libs.barcode.scanning)
            implementation(libs.google.barcode.scanning)
            implementation(libs.qrose)
            implementation(libs.qrose.barcode)
        }
        commonMain.dependencies {
            implementation(projects.data.storage.api.loyaltyCards)
            api(projects.features.api.addLoyaltyCard)
            implementation(projects.features.api.root)

            implementation(compose.components.resources)
            implementation(compose.material3)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.essenty.coroutines)
            implementation(libs.permissions.compose)
            implementation(libs.permissions.camera)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.add_loyalty_card"
}