plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget()

    jvm()

    sourceSets {
        commonMain.dependencies {
            api(projects.data.storage.api.loyaltyCards)

            implementation(libs.datastore.preferences)
        }
    }
}

android {
    namespace = "com.vvl.loyalty_cards.data.storage.impl.loyalty_cards"
}