plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.storage.api.loyaltyCards)
            implementation(projects.data.storage.api.widget)
            api(projects.features.api.root)
            implementation(projects.features.api.deepLinks)
            implementation(projects.features.api.widget)

            implementation(libs.compose.material3)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.decompose.compose.experimental)
            implementation(libs.essenty.coroutines)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}