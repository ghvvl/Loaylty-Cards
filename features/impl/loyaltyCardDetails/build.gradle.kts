plugins {
    id("com.vvl.kmpPlugin")
    id("com.vvl.cmpPlugin")
}

kotlin {
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.storage.api.loyaltyCards)
            api(projects.features.api.loyaltyCardDetails)
            implementation(projects.features.api.root)
            implementation(projects.features.api.widget)
            implementation(projects.features.common)

            implementation(libs.compose.material3)
            implementation(libs.compose.animation)
            implementation(libs.compose.components.resources)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.essenty.coroutines)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}