plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(projects.features.api.addLoyaltyCard)
            implementation(projects.features.api.root)

            implementation(libs.material.compose)
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
        }
    }
}