plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

dependencies {
    api(projects.data.storage.api.loyaltyCards)

    implementation(libs.datastore.preferences)
}

android {
    namespace = "com.vvl.loyalty_cards.data.storage.impl.loyalty_cards"
}