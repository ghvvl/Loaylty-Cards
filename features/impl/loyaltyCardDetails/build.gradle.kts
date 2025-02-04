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
            api(projects.features.api.loyaltyCardDetails)

            implementation(compose.material3)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}