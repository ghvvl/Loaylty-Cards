plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    implementation(projects.data.storage.api.loyaltyCards)

    api(projects.features.api.loyaltyCardsList)
    implementation(projects.features.api.root)

    implementation(libs.material.compose)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
    implementation(libs.essenty.coroutines)
}

android {
    namespace = "com.vvl.loyalty_cards.feature.impl.loyalty_cards_list"
}