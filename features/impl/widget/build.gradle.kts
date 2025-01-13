plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    implementation(projects.data.storage.impl.loyaltyCards)
    api(projects.features.api.widget)

    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
    implementation(libs.zxing)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin)
}

android {
    namespace = "com.vvl.loyalty_cards.features.impl.widget"
    buildFeatures.compose = true
}
