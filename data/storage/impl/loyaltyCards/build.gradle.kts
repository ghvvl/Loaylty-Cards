plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget()

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(projects.data.storage.api.loyaltyCards)

            implementation(libs.datastore)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin)
            implementation(libs.serialization)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.data.storage.impl.loyalty_cards"
}