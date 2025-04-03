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
            implementation(libs.glance.appwidget)
            implementation(libs.glance.material3)
        }

        commonMain.dependencies {
            implementation(projects.data.storage.impl.loyaltyCards)
            implementation(projects.features.common)
            implementation(projects.features.api.deepLinks)
            implementation(projects.features.api.widget)

            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
            implementation(libs.koin.compose)
            implementation(libs.qrose.barcode)
            implementation(libs.qrose)
            implementation(libs.coroutines)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.widget"
}
