plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

dependencies {
    implementation(projects.data.storage.impl.loyaltyCards)

    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
    implementation(libs.zxing)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin)
    implementation(libs.koin.compose)
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.widget"
    buildFeatures.compose = true
}
