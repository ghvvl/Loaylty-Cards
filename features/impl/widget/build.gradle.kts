plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

dependencies {
    implementation(projects.data.storage.impl.loyaltyCards)
    implementation(projects.features.common)

    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin)
    implementation(libs.koin.compose)
    implementation(libs.qrose.barcode)
    implementation(libs.qrose)
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.widget"
    buildFeatures.compose = true
}
