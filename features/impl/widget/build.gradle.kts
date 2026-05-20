plugins {
    id("com.vvl.kmpPlugin")
    id("com.vvl.cmpPlugin")
}

kotlin {
    android.androidResources.enable = true

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.glance.appwidget)
            implementation(libs.glance.material3)
        }

        commonMain.dependencies {
            implementation(projects.data.storage.impl.loyaltyCards)
            implementation(projects.data.storage.impl.widget)
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