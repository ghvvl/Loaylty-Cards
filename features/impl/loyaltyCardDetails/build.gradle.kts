plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.storage.api.loyaltyCards)
            api(projects.features.api.loyaltyCardDetails)
            implementation(projects.features.api.root)
            implementation(projects.features.common)

            implementation(compose.material3)
            implementation(compose.animation)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}