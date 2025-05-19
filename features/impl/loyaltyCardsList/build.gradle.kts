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
        commonMain.dependencies {
            implementation(projects.data.storage.api.loyaltyCards)

            api(projects.features.api.loyaltyCardsList)
            implementation(projects.features.api.root)
            implementation(projects.features.api.widget)
            implementation(projects.features.common)

            implementation(compose.components.resources)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            implementation(libs.essenty.coroutines)
            implementation(libs.coroutines)
            implementation(libs.reorderable)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.loyalty_cards_list"
}