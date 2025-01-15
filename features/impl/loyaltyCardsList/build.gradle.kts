plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()

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
        }
    }
}