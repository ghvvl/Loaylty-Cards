plugins {
    id("com.vvl.kmpPlugin")
    id("com.vvl.cmpPlugin")
}

kotlin {
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
            implementation(projects.features.api.widget)
            implementation(projects.features.common)

            implementation(libs.compose.components.resources)
            implementation(libs.compose.material3)

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